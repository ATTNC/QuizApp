package com.tutuncu.abdullah.QuizApplication.controller;

import com.tutuncu.abdullah.QuizApplication.model.User;
import com.tutuncu.abdullah.QuizApplication.request.LoginRequest;
import com.tutuncu.abdullah.QuizApplication.request.RegisterRequest;
import com.tutuncu.abdullah.QuizApplication.response.LoginResponse;
import com.tutuncu.abdullah.QuizApplication.response.RegisterResponse;
import com.tutuncu.abdullah.QuizApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/add")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> registerUser(@RequestBody RegisterRequest request) {
        return new ResponseEntity<>(userService.registerUser(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request) {
        return new ResponseEntity<>(userService.loginUser(request), HttpStatus.OK);
    }

    @GetMapping("/getUserByUsername")
    public ResponseEntity<User> getUserByUsername(String username) {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }
}
