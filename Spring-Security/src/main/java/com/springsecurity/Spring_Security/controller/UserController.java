package com.springsecurity.Spring_Security.controller;


import com.springsecurity.Spring_Security.entity.UserEntity;
import com.springsecurity.Spring_Security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository  userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/encoderPassword")
    public void saveUserWithEncoder(@RequestParam String username,
                                    @RequestParam String password){

        UserEntity user = new UserEntity();

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setIsActive(true);

        userRepository.save(user);
    }
}
