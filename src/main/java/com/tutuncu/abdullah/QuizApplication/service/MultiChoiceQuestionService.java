package com.tutuncu.abdullah.QuizApplication.service;

import com.tutuncu.abdullah.QuizApplication.model.MultipleChoiceQuestion;
import com.tutuncu.abdullah.QuizApplication.model.User;
import com.tutuncu.abdullah.QuizApplication.repository.MultipleChoiceQuestionRepository;
import com.tutuncu.abdullah.QuizApplication.repository.QuizRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MultiChoiceQuestionService {

    private final MultipleChoiceQuestionRepository repository;
    private final QuizRepository quizRepository;
    private final UserService userService;

    @Autowired
    public MultiChoiceQuestionService(MultipleChoiceQuestionRepository repository, QuizRepository quizRepository, UserService userService) {
        this.repository = repository;
        this.quizRepository = quizRepository;
        this.userService = userService;
    }

    public MultipleChoiceQuestion save(MultipleChoiceQuestion question) {
        return repository.save(question);
    }

    public MultipleChoiceQuestion createQuestion(Long userId, MultipleChoiceQuestion question) {
        User user = userService.getById(userId);
        question.setUser(user);
        return repository.save(question);
    }

    public MultipleChoiceQuestion getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Question not found with id: " + id));
    }

    public Long getUserId(Long id) {
        return repository.getUserIdByQuestionId(id);

    }




}
