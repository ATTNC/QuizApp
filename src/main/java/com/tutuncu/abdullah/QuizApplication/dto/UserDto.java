package com.tutuncu.abdullah.QuizApplication.dto;


import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String gender;
}
