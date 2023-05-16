package com.tutuncu.abdullah.QuizApplication.service;

import com.tutuncu.abdullah.QuizApplication.model.Score;
import com.tutuncu.abdullah.QuizApplication.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoreService {
    private final ScoreRepository repository;

    @Autowired
    public ScoreService(ScoreRepository repository) {
        this.repository = repository;
    }

    public Score createScore(Score score) {
        return repository.save(score);
    }
}
