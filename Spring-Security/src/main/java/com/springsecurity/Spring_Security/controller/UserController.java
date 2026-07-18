package com.springsecurity.Spring_Security.controller;


import com.springsecurity.Spring_Security.dto.AuthRequest;
import com.springsecurity.Spring_Security.entity.UserEntity;
import com.springsecurity.Spring_Security.repository.UserRepository;
import com.springsecurity.Spring_Security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository  userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/encoderPassword")
    public void saveUserWithEncoder(@RequestParam String username,
                                    @RequestParam String password){

        System.out.println("HI");

        UserEntity user = new UserEntity();

        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setIsActive(true);

        userRepository.save(user);
    }


    @PostMapping("/authenticated")
    public String authenticated(@RequestBody AuthRequest authRequest){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.genreateToken(authRequest.getUsername());
        }

        return null;
    }
}
