package com.unicauca.sga.testService.Aplication.Mappers;

import com.unicauca.sga.testService.Domain.Models.Answer;
import com.unicauca.sga.testService.Domain.Models.DTOs.AnswerDTO;
import com.unicauca.sga.testService.Domain.Models.DTOs.QuestionDTO;
import com.unicauca.sga.testService.Domain.Models.DTOs.QuestionListDTO;
import com.unicauca.sga.testService.Domain.Models.Question;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
        newQuestionDTO.setQuestionId(question.getQuestionId());
        newQuestionDTO.setQuestionTitle(question.getQuestionTitle());
        newQuestionDTO.setQuestionText(question.getQuestionText());
        List<AnswerDTO> answerDTOList = new ArrayList<>();
        /*for(Answer answer: question.getAnswers()){
            answerDTOList.add(AnswertoDTO(answer));
        }
        newQuestionDTO.setAnswers(answerDTOList);*/
        return newQuestionDTO;
    }
    /*private AnswerDTO AnswertoDTO(Answer answer){
        AnswerDTO newAnswerDTO = new AnswerDTO();
        newAnswerDTO.setAnswerId(answer.getAnswerId());
        newAnswerDTO.setAnswerText(answer.getAnswerText());
        return newAnswerDTO;
    }*/
}
