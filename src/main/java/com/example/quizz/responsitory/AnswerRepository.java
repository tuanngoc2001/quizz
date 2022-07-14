package com.example.quizz.responsitory;

import com.example.quizz.model.Answer;
import com.example.quizz.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer> {
    List<Answer> findAllByNameContaining(String name);
    Iterable<Answer> findAllByQuestionId(int id);
}
