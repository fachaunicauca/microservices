package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Aplication.Mappers.QuestionListDTOMapper;
import com.unicauca.sga.testService.Domain.Exceptions.InsufficientQuestionsException;
import com.unicauca.sga.testService.Domain.Exceptions.NoQuestionsException;
import com.unicauca.sga.testService.Domain.Exceptions.NotFoundException;
import com.unicauca.sga.testService.Domain.Model.Answer;
import com.unicauca.sga.testService.Domain.Model.DTOs.QuestionListDTO;
import com.unicauca.sga.testService.Domain.Model.DTOs.StudentAnswerDTO;
import com.unicauca.sga.testService.Domain.Model.DTOs.StudentTestResponseDTO;
import com.unicauca.sga.testService.Domain.Model.Question;
import com.unicauca.sga.testService.Domain.Model.Subject;
import com.unicauca.sga.testService.Domain.Model.Test;
import com.unicauca.sga.testService.Domain.Ports.Services.IAnswerService;
import com.unicauca.sga.testService.Domain.Ports.Services.IQuestionService;
import com.unicauca.sga.testService.Domain.Ports.Services.ISubjectService;
import com.unicauca.sga.testService.Domain.Ports.Services.ITestService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TakeTestService {

    private final QuestionListDTOMapper questionListDTOMapper;

    private final IQuestionService questionService;
    private final IAnswerService answerService;
    private final ISubjectService subjectService;
    private final ITestService testService;

    private static final int num_of_questions = 20;

    public TakeTestService(QuestionListDTOMapper questionListDTOMapper,
                           IQuestionService questionService,
                           IAnswerService answerService,
                           ISubjectService subjectService,
                           ITestService testService) {
        this.questionListDTOMapper=questionListDTOMapper;
        this.questionService = questionService;
        this.answerService=answerService;
        this.subjectService=subjectService;
        this.testService=testService;
    }

    /*
     * First, the user sends the data to validate that he can present
     * the evaluation. Once, the data is validated, the exam questions
     * are returned. If the data is invalid, an exception is sent.
     */
    @Transactional(readOnly = true)
    public QuestionListDTO getTestQuestions(String subject_name,
                                            Long student_code,
                                            String teacher_name){
        //Validate that the teacher exists. Teachers Microservice call
        //Validate that the student code exists. Teachers Microservice call
        //Validate that the subject exists.
        if(!subjectService.isPresent(subject_name)){
            throw new NotFoundException("No se encontro la materia "+subject_name+".");
        }

        //Get and return the Questions by Subject
        List<Question> questionList = questionService.getRandomQuestionsBySubject(subject_name, num_of_questions);
        if(questionList.isEmpty()) {
            throw new NoQuestionsException("La materia " + subject_name + " no tiene preguntas asociadas.");
        }else if(questionList.size()<num_of_questions){
            throw new InsufficientQuestionsException("La materia " + subject_name +" no tiene la cantidad de preguntas minimas.");
        }
        return questionListDTOMapper.toDTO(questionList);
    }

    public float saveTest(StudentTestResponseDTO studentTestResponseDTO){
                //Score the test
        float test_score = scoreTest(studentTestResponseDTO);
        if(!subjectService.isPresent(studentTestResponseDTO.getSubjectName())){
            throw new NotFoundException("No se encontro la materia "+studentTestResponseDTO.getSubjectName()+".");
        }
        Subject subject = subjectService.getSubjectById(studentTestResponseDTO.getSubjectName());
        //Create a new test
        Test newTest = new Test();
        newTest.setTeacherName(studentTestResponseDTO.getTeacherName());
        newTest.setStudentId(studentTestResponseDTO.getStudentCode());
        newTest.setSubject(subject);
        newTest.setNumOfQuestions(num_of_questions);
        newTest.setTestDate(studentTestResponseDTO.getTestDate());
        newTest.setTestScore(test_score);
        
        testService.saveTest(newTest);
        //Send the test score
        return test_score;
    }

    private float scoreTest(StudentTestResponseDTO studentTestResponseDTO){
        int correctAnswersCount = 0;
        boolean isCorrect;
        for(StudentAnswerDTO studentAnswerDTO: studentTestResponseDTO.getStudentResponse()){
            //If answer id is a list, it means that there are multiple answers
            if(studentAnswerDTO.getAnswersIds().size()!=1) {
                List<Long> student_answers = studentAnswerDTO.getAnswersIds();
                //Get all the question answers
                List<Answer> answers = answerService.getAllAnswersByQuestion(studentAnswerDTO.getQuestionId());
                if(answers.isEmpty()){
                    throw new NotFoundException("La pregunta con id: "+studentAnswerDTO.getQuestionId()+" no tiene respuestas registradas.");
                }
                //Get the correct answers
                List<Long> correct_answers = answers.stream()
                        .filter(Answer::isCorrect)
                        .map(Answer::getAnswerId)
                        .toList();
                //If the list are the same it means that the answers are correct
                if (correct_answers.size() == student_answers.size()) {
                    //Ignore the order
                    Set<Long> correctSet = new HashSet<>(correct_answers);
                    Set<Long> studentSet = new HashSet<>(student_answers);

                    if (correctSet.equals(studentSet)) correctAnswersCount++;
                }
            }//If the answer id isn't a list, then just validate that is the correct answer
            else{
                if(!answerService.isPresent(studentAnswerDTO.getAnswersIds().get(0))){
                    throw new NotFoundException("La respuesta con id: "+studentAnswerDTO.getAnswersIds().get(0)+" no esta registrada.");
                }
                isCorrect=answerService.getAnswerById(studentAnswerDTO.getAnswersIds().get(0)).isCorrect();

                if(isCorrect) correctAnswersCount++;
            }
        }
        return (float) correctAnswersCount / num_of_questions;
    }

    public short getTries(Date request_date, Long student_code){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(request_date);

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;

        String start, end;

        if(month<=6){
            start = year+"-01-01";
            end = year+"-06-30";
        }else{
            start = year+"-07-01";
            end = year+"-12-31";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start_date = LocalDate.parse(start, formatter);
        LocalDate end_date = LocalDate.parse(end, formatter);
        try{
            List<Test> semesterTestList = testService.getTestBySemesterAndStudentCode(start_date,end_date,student_code);
            if(!semesterTestList.isEmpty()){
                if(semesterTestList.stream().anyMatch(test -> test.getTestScore() >= 0.7)) {
                    return (short) -1; //Temporaly test already passed code.
                }
            }
            return (short) semesterTestList.size();
        } catch (Exception e) {
            throw new NotFoundException("No se encontro el estudiante con codigo "+student_code+".");
        }
    }
}
