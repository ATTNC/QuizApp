package com.tutuncu.abdullah.QuizApplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table
@Data
public class MultipleChoiceQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionText;
    private String option_1;
    private String option_2;
    private String option_3;
    private String option_4;
    private String correctOption;
    private int score;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Quiz> quizzes;
    @OneToMany(mappedBy = "multiQuestion", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Answer> answers;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

}
