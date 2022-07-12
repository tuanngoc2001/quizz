package com.example.quizz.controller;

import com.example.quizz.common.ResponseData;
import com.example.quizz.model.Answer;
import com.example.quizz.model.Question;
import com.example.quizz.responsitory.AnswerRepository;
import com.example.quizz.responsitory.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/v1/question")
public class QuestionController {
    @Autowired
    QuestionRepository repository;

    @GetMapping
    List<Question> GetAllQuestion()
    {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    Optional<Question> GetQuestionById(@PathVariable int id)
    {
        return repository.findById(id);
    }
//    @PostMapping
//    ResponseEntity<ResponseData> InsertQuestion(@RequestBody Question question)
//    {
//        List<Question>lstqu=this.repository.findByName(question.getContent().trim());
//        if(lstqu.size()>0)
//            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseData("Fail","INSERT PRODUCT FAIL",""));
//        return ResponseEntity.status(HttpStatus.OK).body(new ResponseData("Success","Query product success",this.repository.save(question)));
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> DeleteById(@PathVariable int id)
    {
        boolean check= repository.existsById(id);//kiểm tra xem id này có không
        if(check)
        {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseData("Success","Delete PRODUCT Success",""));

        }
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseData("Fail","NOTFOUND PRODUCT BY ID "+id,""));
    }


    @PutMapping("/{id}")
    ResponseEntity<ResponseData>update(@PathVariable int id,@RequestBody Question question)
    {

        Question updatequestion=repository.findById(id).map(
                pr -> {
                    pr.setId(question.getId());
                    pr.setContent(question.getContent());

                    pr.setType(question.getType());
                    return repository.save(pr);
                }).orElseGet(()->{
                    question.setId(id);
                    return repository.save(question);
                });
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseData("Success","Update PRODUCT Success",updatequestion));
    }
}
