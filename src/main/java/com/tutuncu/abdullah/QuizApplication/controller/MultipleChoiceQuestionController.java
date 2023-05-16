package com.tutuncu.abdullah.QuizApplication.controller;

import com.tutuncu.abdullah.QuizApplication.model.MultipleChoiceQuestion;
import com.tutuncu.abdullah.QuizApplication.service.MultiChoiceQuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/multipleQuestion")
public class MultipleChoiceQuestionController {

    private final MultiChoiceQuestionService service;

    @Autowired
    public MultipleChoiceQuestionController(MultiChoiceQuestionService service) {
        this.service = service;
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<MultipleChoiceQuestion> createQuestion(@RequestBody MultipleChoiceQuestion question, @PathVariable Long id) {
        return new ResponseEntity<>(service.createQuestion(id, question), HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<MultipleChoiceQuestion> getQuestion(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }
}
