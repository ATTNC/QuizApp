package com.tutuncu.abdullah.QuizApplication.service;

import com.tutuncu.abdullah.QuizApplication.model.MultipleChoiceQuestion;
import com.tutuncu.abdullah.QuizApplication.model.OpenEndedQuestion;
import com.tutuncu.abdullah.QuizApplication.model.User;
import com.tutuncu.abdullah.QuizApplication.repository.OpenEndedQuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenEndedQuestionService {
    private final OpenEndedQuestionRepository repository;
    private final UserService userService;

    @Autowired
    public OpenEndedQuestionService(OpenEndedQuestionRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    public OpenEndedQuestion save(OpenEndedQuestion question) {
        return repository.save(question);
    }

    public OpenEndedQuestion createQuestion(Long userId, OpenEndedQuestion question) {
        User user = userService.getById(userId);
        question.setUser(user);
        return repository.save(question);
    }

    public OpenEndedQuestion getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + id));
    }

    public Long getUserId(Long id) {
        return repository.getUserIdByQuestionId(id);

    }

}
