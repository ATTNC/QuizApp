package com.tutuncu.abdullah.QuizApplication.service;

import com.tutuncu.abdullah.QuizApplication.model.*;
import com.tutuncu.abdullah.QuizApplication.repository.AnswerRepository;
import org.springframework.stereotype.Service;

@Service
public class AnswerService {

    private final AnswerRepository repository;
    private final UserService userService;
    private final QuizService quizService;
    private final MultiChoiceQuestionService multiService;
    private final FillBlankQuestionService fillService;
    private final OpenEndedQuestionService openEndService;

    public AnswerService(AnswerRepository repository, UserService userService, QuizService quizService, MultiChoiceQuestionService multiService, FillBlankQuestionService fillService, OpenEndedQuestionService openEndService) {
        this.repository = repository;
        this.userService = userService;
        this.quizService = quizService;
        this.multiService = multiService;
        this.fillService = fillService;
        this.openEndService = openEndService;
    }


    public Answer createAnswerForMultiQuestion(Long userId, Long quizId, Long questionId, String answerText) {
        User user = userService.getById(userId);
        Quiz quiz = quizService.getById(quizId);
        MultipleChoiceQuestion question = multiService.getById(questionId);

        Long checkUserId = user.getId();
        Long checkQuizUserId = quiz.getUser().getId();
        Long checkQuestionUserId = question.getUser().getId();

        Boolean checkQuestion = quizService.existsMultipleChoiceQuestion(questionId, quizId);

        // "bu" soruyu oluşturan cevap veremez,"bu" quizi oluşturan cevap veremez.

        if (checkQuestion) {
            Answer answer = new Answer();
            answer.setAnswer(answerText);
            answer.setUser(user);
            answer.setMultiQuestion(question);
            answer.setQuiz(quiz);

            question.getAnswers().add(answer);
            multiService.save(question);
            return answer;
        } else throw new RuntimeException("Answer cant created");


    }

    public Answer createAnswerForFillQuestion(Long userId, Long quizId, Long questionId, String answerText) {
        User user = userService.getById(userId);
        Quiz quiz = quizService.getById(quizId);
        FillBlankQuestion question = fillService.getById(questionId);

        Boolean checkQuestion = quizService.existsFillBankQuestion(questionId, quizId);

        if (checkQuestion) {
            Answer answer = new Answer();
            answer.setAnswer(answerText);
            answer.setUser(user);
            answer.setFillQuestion(question);
            answer.setQuiz(quiz);

            question.getAnswers().add(answer);
            fillService.save(question);
            return answer;
        } else throw new RuntimeException("Answer cant created");
    }

    public Answer createAnswerForOpenEndedQuestion(Long userId, Long quizId, Long questionId, String answerText) {
        User user = userService.getById(userId);
        Quiz quiz = quizService.getById(quizId);
        OpenEndedQuestion question = openEndService.getById(questionId);
        Boolean checkQuestion = quizService.existsOpenEndedQuestion(questionId, quizId);

        if (checkQuestion) {
            Answer answer = new Answer();
            answer.setAnswer(answerText);
            answer.setUser(user);
            answer.setOpenEndQuestion(question);
            answer.setQuiz(quiz);

            question.getAnswers().add(answer);
            openEndService.save(question);
            return answer;
        } else throw new RuntimeException("Answer cant created");

    }

}
