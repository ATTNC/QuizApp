package com.tutuncu.abdullah.QuizApplication.repository;

import com.tutuncu.abdullah.QuizApplication.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
}
