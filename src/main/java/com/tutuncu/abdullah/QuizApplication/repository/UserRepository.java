package com.tutuncu.abdullah.QuizApplication.repository;

import com.tutuncu.abdullah.QuizApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u.id FROM User u WHERE u.id = :id")
    Long findIdById(@Param("id") Long id);
    User findByUsername(String username);
}
