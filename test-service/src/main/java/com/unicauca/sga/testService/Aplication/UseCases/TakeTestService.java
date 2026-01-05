package com.unicauca.sga.testService.Aplication.UseCases;

import com.unicauca.sga.testService.Aplication.Mappers.QuestionListDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TakeTestService {

    private final QuestionListDTOMapper questionListDTOMapper;
    /*
    private final IQuestionRepository questionRepository;
    private final IAnswerRepository answerRepository;
    private final ISubjectRepository subjectRepository;
    private final ITestRepository testRepository;

    private static final int num_of_questions = 20;*/

    /*
     * First, the user sends the data to validate that he can present
     * the evaluation. Once the data is validated, the exam questions
     * are returned. If the data is invalid, an exception is sent.
     */
    /*
    @Transactional(readOnly = true)
    public QuestionListDTO getTestQuestions(String subject_name,
                                            Long student_code,
                                            String teacher_name){
        //Validate that the teacher exists. Teacher Microservice call
        //Validate that the student code exists. Teacher Microservice call
        //Validate that the subject exists.
        if(!subjectRepository.isPresent(subject_name)){
            throw new NotFoundException("No se encontró la materia "+subject_name+".");
        }

        //Get and return the Questions by Subject
        List<Question> questionList = questionRepository.getRandomQuestionsBySubject(subject_name, num_of_questions);
        if(questionList.isEmpty()) {
            throw new NoQuestionsException("La materia " + subject_name + " no tiene preguntas asociadas.");
        }else if(questionList.size()<num_of_questions){
            throw new InsufficientQuestionsException("La materia " + subject_name +" no tiene la cantidad de preguntas mínima.");
        }
        return questionListDTOMapper.toDTO(questionList);
    }*/

    /*public float saveTest(StudentTestResponseDTO studentTestResponseDTO){
        //Score the test
        float test_score = scoreTest(studentTestResponseDTO);
        if(!subjectRepository.isPresent(studentTestResponseDTO.getSubjectName())){
            throw new NotFoundException("No se encontró la materia "+studentTestResponseDTO.getSubjectName()+".");
        }
        Subject subject = subjectRepository.getSubjectById(studentTestResponseDTO.getSubjectName());
        //Create a new test
        Test newTest = new Test();
        newTest.setTeacherName(studentTestResponseDTO.getTeacherName());
        newTest.setStudentId(studentTestResponseDTO.getStudentCode());
        newTest.setSubject(subject);
        newTest.setNumOfQuestions(num_of_questions);
        newTest.setTestDate(studentTestResponseDTO.getTestDate());
        newTest.setTestScore(test_score);
        
        testRepository.save(newTest);
        //Send the test score
        return test_score;
    }*/

    /*private float scoreTest(StudentTestResponseDTO studentTestResponseDTO){
        int correctAnswersCount = 0;
        boolean isCorrect;
        for(StudentAnswerDTO studentAnswerDTO: studentTestResponseDTO.getStudentResponse()){
            //If answer id is a list, it means that there are multiple answers
            if(studentAnswerDTO.getAnswersIds().size()!=1) {
                List<Long> student_answers = studentAnswerDTO.getAnswersIds();
                //Get all the question answers
                List<Answer> answers = answerRepository.getAllAnswersByQuestion(studentAnswerDTO.getQuestionId());
                if(answers.isEmpty()){
                    throw new NotFoundException("La pregunta con id: "+studentAnswerDTO.getQuestionId()+" no tiene respuestas registradas.");
                }
                //Get the correct answers
                List<Long> correct_answers = answers.stream()
                        .filter(Answer::isCorrect)
                        .map(Answer::getAnswerId)
                        .toList();
                //If the list is the same, it means that the answers are correct
                if (correct_answers.size() == student_answers.size()) {
                    //Ignore the order
                    Set<Long> correctSet = new HashSet<>(correct_answers);
                    Set<Long> studentSet = new HashSet<>(student_answers);

                    if (correctSet.equals(studentSet)) correctAnswersCount++;
                }
            }//If the answer id isn't a list, then just validate that is the correct answer
            else{
                if(!answerRepository.isPresent(studentAnswerDTO.getAnswersIds().get(0))){
                    throw new NotFoundException("La respuesta con id: "+studentAnswerDTO.getAnswersIds().get(0)+" no esta registrada.");
                }
                isCorrect=answerRepository.getAnswerById(studentAnswerDTO.getAnswersIds().get(0)).isCorrect();

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
            List<Test> semesterTestList = testRepository.getTestBySemesterAndStudentCode(start_date,end_date,student_code);
            if(!semesterTestList.isEmpty()){
                if(semesterTestList.stream().anyMatch(test -> test.getTestScore() >= 0.7)) {
                    return (short) -1; //Temporally test already passed code.
                }
            }
            return (short) semesterTestList.size();
        } catch (Exception e) {
            throw new NotFoundException("No se encontró el estudiante con code "+student_code+".");
        }
    }*/
}
