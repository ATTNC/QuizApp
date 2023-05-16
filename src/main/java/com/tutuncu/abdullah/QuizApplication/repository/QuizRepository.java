package com.tutuncu.abdullah.QuizApplication.repository;

import com.tutuncu.abdullah.QuizApplication.model.MultipleChoiceQuestion;
import com.tutuncu.abdullah.QuizApplication.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    // repository query oluşturman lazım eğer quiz içerisindeki user id ve question içerisindeki user'idler aynı ise burdaki questionı quiz içerisine pushla.

    @Query("SELECT q.user.id FROM Quiz q WHERE q.id = :quizId")
    Long getUserIdByQuizId(@Param("quizId") Long quizId);

    @Query("SELECT CASE WHEN COUNT(mq) > 0 THEN TRUE ELSE FALSE END FROM Quiz q JOIN q.multiples mq WHERE q.id = :quizId AND mq.id = :questionId")
    Boolean existsMultipleChoiceQuestionByIdAndQuizId(@Param("questionId") Long questionId, @Param("quizId") Long quizId);

    @Query("SELECT CASE WHEN COUNT(fb) > 0 THEN TRUE ELSE FALSE END FROM Quiz q JOIN q.fillBlanks fb WHERE q.id = :quizId AND fb.id = :questionId")
    Boolean existsFillBlankQuestionByIdAndQuizId(@Param("questionId") Long questionId, @Param("quizId") Long quizId);

    @Query("SELECT CASE WHEN COUNT(oe) > 0 THEN TRUE ELSE FALSE END FROM Quiz q JOIN q.openEnds oe WHERE q.id = :quizId AND oe.id = :questionId")
    Boolean existsOpenEndedQuestionByIdAndQuizId(@Param("questionId") Long questionId, @Param("quizId") Long quizId);

}
