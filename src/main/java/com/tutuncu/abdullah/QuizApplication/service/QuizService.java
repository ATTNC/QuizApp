package com.tutuncu.abdullah.QuizApplication.service;

import com.tutuncu.abdullah.QuizApplication.model.*;
import com.tutuncu.abdullah.QuizApplication.repository.QuizRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository repository;

    private final UserService userService;
    private final MultiChoiceQuestionService multiService;
    private final FillBlankQuestionService fillService;
    private final OpenEndedQuestionService openEndService;


    public Quiz createQuiz(Long userId, Quiz quiz) {
        User user = userService.getById(userId);
        quiz.setUser(user);
        quiz.setCreateDate(LocalDate.now());
        return repository.save(quiz);
    }


    public void addMultipleQuestionToQuiz(Long quizId, Long multiId, Long userId) {

        Long user = userService.checkUserId(userId);
        Quiz quiz = getById(quizId);
        MultipleChoiceQuestion multiQuestion = multiService.getById(multiId);


        Long multiUser = multiService.getUserId(multiId);
        Long quizUser = repository.getUserIdByQuizId(quizId);


        if (Objects.equals(user, quizUser) && Objects.equals(user, multiUser) && Objects.equals(multiUser, quizUser)) {

            quiz.getMultiples().add(multiQuestion);
            multiQuestion.getQuizzes().add(quiz);
            quiz.setStatus(true);
            repository.save(quiz);
            multiService.save(multiQuestion);
        } else throw new RuntimeException("Question cant be added to quiz");

    }

    public void addFillQuestionToQuiz(Long quizId, Long fillId, Long userId) {

        Long user = userService.checkUserId(userId);
        Quiz quiz = getById(quizId);
        FillBlankQuestion fillQuestion = fillService.getById(fillId);


        Long fillUser = fillService.getUserId(fillId);
        Long quizUser = repository.getUserIdByQuizId(quizId);


        if (Objects.equals(user, quizUser) && Objects.equals(user, fillUser) && Objects.equals(fillUser, quizUser)) {

            quiz.getFillBlanks().add(fillQuestion);
            fillQuestion.getQuizzes().add(quiz);
            quiz.setStatus(true);
            repository.save(quiz);
            fillService.save(fillQuestion);
        } else throw new RuntimeException("Question cant be added to quiz");

    }

    public void addOpenEndQuestionToQuiz(Long quizId, Long openEndId, Long userId) {

        Long user = userService.checkUserId(userId);
        Quiz quiz = getById(quizId);
        OpenEndedQuestion openEndedQuestion = openEndService.getById(openEndId);


        Long openEndUser = openEndService.getUserId(openEndId);
        Long quizUser = repository.getUserIdByQuizId(quizId);


        if (Objects.equals(user, quizUser) && Objects.equals(user, openEndUser) && Objects.equals(openEndUser, quizUser)) {

            quiz.getOpenEnds().add(openEndedQuestion);
            openEndedQuestion.getQuizzes().add(quiz);
            quiz.setStatus(true);
            repository.save(quiz);
            openEndService.save(openEndedQuestion);
        } else throw new RuntimeException("Question cant be added to quiz");

    }


    public List<Quiz> getAll() {
        return repository.findAll();
    }

    public Quiz getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Quiz not found with id: " + id));
    }

    public Boolean existsMultipleChoiceQuestion(Long questionId, Long quizId) {
        return repository.existsMultipleChoiceQuestionByIdAndQuizId(questionId, quizId);
    }

    public Boolean existsFillBankQuestion(Long questionId, Long quizId) {
        return repository.existsFillBlankQuestionByIdAndQuizId(questionId, quizId);
    }

    public Boolean existsOpenEndedQuestion(Long questionId, Long quizId) {
        return repository.existsOpenEndedQuestionByIdAndQuizId(questionId, quizId);
    }
}
