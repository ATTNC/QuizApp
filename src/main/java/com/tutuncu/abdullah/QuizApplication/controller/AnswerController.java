package com.tutuncu.abdullah.QuizApplication.controller;

import com.tutuncu.abdullah.QuizApplication.model.Answer;
import com.tutuncu.abdullah.QuizApplication.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    private final AnswerService answerService;

    @Autowired
    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @PostMapping("/createMultiAnswer/{userId}/{quizId}/{questionId}")
    public ResponseEntity<Answer> createMultiAnswer(@PathVariable Long userId, @PathVariable Long quizId, @PathVariable Long questionId,
                                                    @RequestBody String answerText) {
        return new ResponseEntity<>(answerService.createAnswerForMultiQuestion(userId, quizId, questionId, answerText), HttpStatus.CREATED);
    }

    @PostMapping("/createFillAnswer/{userId}/{quizId}/{questionId}")
    public ResponseEntity<Answer> createFillAnswer(@PathVariable Long userId, @PathVariable Long quizId, @PathVariable Long questionId,
                                                   @RequestBody String answerText) {
        return new ResponseEntity<>(answerService.createAnswerForFillQuestion(userId, quizId, questionId, answerText), HttpStatus.CREATED);
    }

    @PostMapping("/createOpenEndedAnswer/{userId}/{quizId}/{questionId}")
    public ResponseEntity<Answer> createOpenEndedAnswer(@PathVariable Long userId, @PathVariable Long quizId, @PathVariable Long questionId,
                                                        @RequestBody String answerText) {
        return new ResponseEntity<>(answerService.createAnswerForOpenEndedQuestion(userId, quizId, questionId, answerText), HttpStatus.CREATED);
    }

}
