package com.tutuncu.abdullah.QuizApplication.repository;

import com.tutuncu.abdullah.QuizApplication.model.MultipleChoiceQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MultipleChoiceQuestionRepository extends JpaRepository<MultipleChoiceQuestion, Long> {

    @Query("SELECT mcq.user.id FROM MultipleChoiceQuestion mcq WHERE mcq.id = :questionId")
    Long getUserIdByQuestionId(@Param("questionId") Long questionId);


}
