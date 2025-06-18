package com.unicauca.sga.testService.Aplication.Mappers;

import com.unicauca.sga.testService.Domain.Model.Answer;
import com.unicauca.sga.testService.Domain.Model.DTOs.AnswerDTO;
import com.unicauca.sga.testService.Domain.Model.DTOs.QuestionDTO;
import com.unicauca.sga.testService.Domain.Model.DTOs.QuestionListDTO;
import com.unicauca.sga.testService.Domain.Model.Question;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Component
public class QuestionListDTOMapper {
    public QuestionListDTO toDTO(List<Question> questions){
        QuestionListDTO newQuestionListDTO = new QuestionListDTO();
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for(Question question: questions){
            questionDTOList.add(QuestiontoDTO(question));
        }
        newQuestionListDTO.setQuestions(questionDTOList);
        return newQuestionListDTO;
    }
    private QuestionDTO QuestiontoDTO(Question question){
        QuestionDTO newQuestionDTO = new QuestionDTO();
        newQuestionDTO.setQuestion_id(question.getQuestion_id());
        newQuestionDTO.setQuestion_title(question.getQuestion_title());
        newQuestionDTO.setQuestion_text(question.getQuestion_text());
        String base64image = Base64.getEncoder().encodeToString(question.getQuestion_image());
        newQuestionDTO.setQuestion_image(base64image);
        List<AnswerDTO> answerDTOList = new ArrayList<>();
        for(Answer answer: question.getAnswers()){
            answerDTOList.add(AnswertoDTO(answer));
        }
        newQuestionDTO.setAnswers(answerDTOList);
        return newQuestionDTO;
    }
    private AnswerDTO AnswertoDTO(Answer answer){
        AnswerDTO newAnswerDTO = new AnswerDTO();
        newAnswerDTO.setAnswer_id(answer.getAnswer_id());
        newAnswerDTO.setAnswer_text(answer.getAnswer_text());
        return newAnswerDTO;
    }
}
