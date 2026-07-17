package com.springsecurity.Spring_Security.controller;

import com.springsecurity.Spring_Security.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class SecurityController {


    @GetMapping("check")
    public String check(){
        System.out.println("hello");
        return "Hello saurabh";
    }



}
