package com.unicauca.sga.testService.Aplication.Services;

import com.unicauca.sga.testService.Domain.Model.Question;
import com.unicauca.sga.testService.Domain.Ports.Repositories.IQuestionRepository;
import com.unicauca.sga.testService.Domain.Ports.Services.IQuestionService;
import com.unicauca.sga.testService.Infrastructure.Persistence.Tables.QuestionTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService implements IQuestionService {

    private final IQuestionRepository questionRepository;

    public QuestionService(IQuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public List<Question> getAllQuestions(){
        return (List<Question>) questionRepository.findAll();
    }

    @Override
    public Question getQuestionById(long id){
        return questionRepository.findById(id);
    }

    @Override
    public void saveQuestion(Question question){
        questionRepository.save(question);
    }

    @Override
    public void deleteQuestion(Question question){
        questionRepository.delete(question);
    }

    @Override
    public void deleteQuestionById(long id){
        questionRepository.deleteById(id);
    }

    @Override
    public void updateQuestion(Question question){
        questionRepository.save(question);
    }

    @Override
    public List<Question> getRandomQuestionsBySubject(String subject_name, int n){
        return questionRepository.findRandomBySubject(subject_name,n);
    }

    @Override
    public boolean isPresent(long id) {
        return questionRepository.isPresent(id);
    }
}
