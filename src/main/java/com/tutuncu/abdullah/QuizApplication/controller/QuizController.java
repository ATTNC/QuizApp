package com.tutuncu.abdullah.QuizApplication.controller;

import com.tutuncu.abdullah.QuizApplication.model.Quiz;
import com.tutuncu.abdullah.QuizApplication.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;


    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }


    @PostMapping("/create/{id}")
    public ResponseEntity<Quiz> create(@PathVariable Long id, @RequestBody Quiz quiz) {
        return new ResponseEntity<>(quizService.createQuiz(id, quiz), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Quiz>> getAll() {
        return new ResponseEntity<>(quizService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/addMulti/{quizId}/{multiId}/{userId}")
    public void addMultiQuestion(@PathVariable Long quizId, @PathVariable Long multiId, @PathVariable Long userId) {
        quizService.addMultipleQuestionToQuiz(quizId, multiId, userId);
    }

    @PutMapping("/addFill/{quizId}/{fillId}/{userId}")
    public void addFillQuestion(@PathVariable Long quizId, @PathVariable Long fillId, @PathVariable Long userId) {
        quizService.addFillQuestionToQuiz(quizId, fillId, userId);
    }

    @PutMapping("/addOpenEnd/{quizId}/{openEndId}/{userId}")
    public void addOpenEnd(@PathVariable Long quizId, @PathVariable Long openEndId, @PathVariable Long userId) {
        quizService.addOpenEndQuestionToQuiz(quizId, openEndId, userId);
    }


}
