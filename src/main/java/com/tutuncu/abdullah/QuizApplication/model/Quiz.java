package com.tutuncu.abdullah.QuizApplication.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String quizName;
    private String Description;
    @NotBlank(message = "Topic name cannot be empty")
    @Column(length = 50)
    private String topicName;
    private LocalDate createDate;
    private boolean status;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "quiz_MultiQuestion", joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    @JsonIgnoreProperties("quiz")
    private List<MultipleChoiceQuestion> multiples;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "quiz_FillQuestion", joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    @JsonIgnoreProperties("quiz")
    private List<FillBlankQuestion> fillBlanks;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "quiz_OpenEndQuestion", joinColumns = @JoinColumn(name = "quiz_id"),
            inverseJoinColumns = @JoinColumn(name = "question_id"))
    @JsonIgnoreProperties("quiz")
    private List<OpenEndedQuestion> openEnds;
    @OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Score> score;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;


    // score için cevaplar ve soruları düşün ve araştır
    // answer entity oluştur ve score,quiz,question ile eşleştir.
}
