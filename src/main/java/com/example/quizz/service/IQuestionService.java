package com.example.quizz.service;

import com.example.quizz.common.ResponseData;
import com.example.quizz.model.Question;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface IQuestionService {
    Iterable<Question> GetAllQuestion();
    ResponseEntity<ResponseData> InsertQuestion(Question question);
    ResponseEntity<ResponseData> DeleteById( int id);
    ResponseEntity<ResponseData>update(int id,Question question);
}
