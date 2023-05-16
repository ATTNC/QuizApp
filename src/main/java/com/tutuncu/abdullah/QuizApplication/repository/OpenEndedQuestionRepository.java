package com.tutuncu.abdullah.QuizApplication.repository;

import com.tutuncu.abdullah.QuizApplication.model.OpenEndedQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OpenEndedQuestionRepository extends JpaRepository<OpenEndedQuestion, Long> {

    @Query("SELECT mcq.user.id FROM OpenEndedQuestion mcq WHERE mcq.id = :questionId")
    Long getUserIdByQuestionId(@Param("questionId") Long questionId);
}
