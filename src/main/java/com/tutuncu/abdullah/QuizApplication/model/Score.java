package com.tutuncu.abdullah.QuizApplication.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table
public class Score {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
    private LocalDate scoreDate;
    private int totalScore;
}
