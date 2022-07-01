package com.example.quizz.service;

import com.example.quizz.common.ResponseData;
import com.example.quizz.model.Question;
import com.example.quizz.responsitory.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class QuestionService implements IQuestionService{
    @Autowired
    QuestionRepository repository;
    @Override
    public Iterable<Question> GetAllQuestion() {
        return null;
    }

    @Override
    public ResponseEntity<ResponseData> InsertQuestion(Question question) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseData> DeleteById(int id) {
        return null;
    }

    @Override
    public ResponseEntity<ResponseData> update(int id, Question question) {
        return null;
    }
}
