package com.tutuncu.abdullah.QuizApplication.service;

import com.tutuncu.abdullah.QuizApplication.model.User;
import com.tutuncu.abdullah.QuizApplication.repository.UserRepository;
import com.tutuncu.abdullah.QuizApplication.request.LoginRequest;
import com.tutuncu.abdullah.QuizApplication.request.RegisterRequest;
import com.tutuncu.abdullah.QuizApplication.response.LoginResponse;
import com.tutuncu.abdullah.QuizApplication.response.RegisterResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;


    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    //Using to Question To Quiz
    public Long checkUserId(Long id) {
        return userRepository.findIdById(id);
    }

    public User updateUser(Long id, User updatedUser) {
        User user = getById(id);
        user.setFirstname(updatedUser.getFirstname());
        user.setLastname(updatedUser.getLastname());
        user.setUsername(updatedUser.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(updatedUser.getPassword()));
        user.setGender(updatedUser.getGender());
        return userRepository.save(user);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public RegisterResponse registerUser(RegisterRequest request) {

        User user = new User();
        user.setFirstname(request.getFirstname());
        user.setLastname(request.getLastname());
        user.setUsername(request.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        user.setGender(request.getGender());

        userRepository.save(user);
        RegisterResponse response = new RegisterResponse();
        response.setMessage("Register Successful");
        return response;

    }

    public String getUserPassword(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user.getPassword();
        } else throw new EntityNotFoundException("User not found");
    }

    public LoginResponse loginUser(LoginRequest request) {
        User checkUser = userRepository.findByUsername(request.getUsername());
        if (new BCryptPasswordEncoder().matches(request.getPassword(), checkUser.getPassword())) {
            LoginResponse response = new LoginResponse();
            response.setMessage("Login Successful");
            response.setUserId(checkUser.getId());
            return response;

        } else throw new RuntimeException("Login failed");

    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }


}
