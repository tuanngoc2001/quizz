package com.example.quizz.controller;

import com.example.quizz.common.ResponseData;
import com.example.quizz.model.Answer;
import com.example.quizz.model.Answer;
import com.example.quizz.responsitory.AnswerRepository;
import com.example.quizz.responsitory.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(path = "api/v1/answer")
public class AnswerController {
    @Autowired
    AnswerRepository repository;

    @GetMapping
    public ResponseEntity findAll() {
        return new ResponseEntity(repository.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity getQuestionById(@PathVariable int id) {
        return new ResponseEntity(repository.findAllByQuestionId(id), HttpStatus.OK);
    }
    @GetMapping("/question-management/{id-question}")
    public ResponseEntity<Answer>getAnswerTrue(@RequestBody int id,@RequestBody int isTrue)
    {
        return new ResponseEntity<>(repository.findAllByQuestionIdAndAndIsTrue(id,isTrue),HttpStatus.OK);
    }
    @PostMapping
    ResponseEntity<ResponseData> insertAnswer(@RequestBody Answer Answer)
    {
        List<Answer>lstqu=this.repository.findAllByNameContaining(Answer.getName().trim());
        if(lstqu.size()>0)
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(new ResponseData("Fail","INSERT PRODUCT FAIL",""));
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseData("Success","Query product success",this.repository.save(Answer)));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData> deleteById(@PathVariable int id)
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
    ResponseEntity<ResponseData>update(@PathVariable int id,@RequestBody Answer answer)
    {

        Answer updateAnswer=repository.findById(id).map(
                pr -> {
                    pr.setId(answer.getId());
                    pr.setName(answer.getName());

                    pr.setQuestion(answer.getQuestion());
                    pr.setIsTrue(answer.getIsTrue());
                    return repository.save(pr);
                }).orElseGet(()->{
            answer.setId(id);
            return repository.save(answer);
        });
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseData("Success","Update PRODUCT Success",updateAnswer));
    }
}
