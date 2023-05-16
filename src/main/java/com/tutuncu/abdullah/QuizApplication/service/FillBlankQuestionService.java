package com.tutuncu.abdullah.QuizApplication.service;

import com.tutuncu.abdullah.QuizApplication.model.FillBlankQuestion;
import com.tutuncu.abdullah.QuizApplication.model.MultipleChoiceQuestion;
import com.tutuncu.abdullah.QuizApplication.model.User;
import com.tutuncu.abdullah.QuizApplication.repository.FillBlankQuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FillBlankQuestionService {
    private final FillBlankQuestionRepository repository;
    private final UserService userService;

    @Autowired
    public FillBlankQuestionService(FillBlankQuestionRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public FillBlankQuestion save(FillBlankQuestion question) {
        return repository.save(question);
    }


    public FillBlankQuestion createQuestion(Long userId, FillBlankQuestion question) {
        User user = userService.getById(userId);
        return repository.save(question);
    }

    public FillBlankQuestion getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + id));
    }

    public Long getUserId(Long id) {
        return repository.getUserIdByQuestionId(id);

    }
}
