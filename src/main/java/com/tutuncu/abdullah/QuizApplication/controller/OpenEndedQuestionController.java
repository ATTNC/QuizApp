package com.tutuncu.abdullah.QuizApplication.controller;

import com.tutuncu.abdullah.QuizApplication.model.MultipleChoiceQuestion;
import com.tutuncu.abdullah.QuizApplication.model.OpenEndedQuestion;
import com.tutuncu.abdullah.QuizApplication.service.OpenEndedQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/openEndQuestion")
public class OpenEndedQuestionController {

    private final OpenEndedQuestionService service;

    @Autowired
    public OpenEndedQuestionController(OpenEndedQuestionService service) {
        this.service = service;
    }


    @PostMapping("/create/{id}")
    public ResponseEntity<OpenEndedQuestion> createQuestion(@RequestBody OpenEndedQuestion question, @PathVariable Long id) {
        return new ResponseEntity<>(service.createQuestion(id, question), HttpStatus.CREATED);
    }


}
