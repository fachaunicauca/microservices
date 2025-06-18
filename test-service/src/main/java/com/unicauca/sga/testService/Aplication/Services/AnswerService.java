package com.unicauca.sga.testService.Aplication.Services;

import com.unicauca.sga.testService.Domain.Model.Answer;
import com.unicauca.sga.testService.Domain.Ports.Repositories.IAnswerRepository;
import com.unicauca.sga.testService.Domain.Ports.Services.IAnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerService implements IAnswerService {

    private final IAnswerRepository answerRepository;

    @Override
    public List<Answer> getAllAnswers(){
        return (List<Answer>) answerRepository.findAll();
    }

    @Override
    public Answer getAnswerById(long id){
        return answerRepository.findById(id);
    }

    @Override
    public void saveAnswer(Answer answer){
        answerRepository.save(answer);
    }

    @Override
    public void deleteAnswer(Answer answer){
        answerRepository.delete(answer);
    }

    @Override
    public void deleteAnswerById(long id){
        answerRepository.deleteById(id);
    }

    @Override
    public void updateAnswer(Answer answer){
        answerRepository.save(answer);
    }

    @Override
    public List<Answer> getAllAnswersByQuestion(long q_id) {
        return answerRepository.findByQuestionId(q_id);
    }

    @Override
    public boolean answerIsCorrect(long id){
        return answerRepository.isCorrect(id);
    }

    @Override
    public boolean isPresent(long id) {
        return answerRepository.isPresent(id);
    }
}
