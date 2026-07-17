package com.springsecurity.Spring_Security.service;


import com.springsecurity.Spring_Security.entity.UserEntity;
import com.springsecurity.Spring_Security.repository.UserRepository;
import org.hibernate.annotations.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.print.attribute.UnmodifiableSetException;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService {


    @Autowired
    private UserRepository userRepository;



    public UserEntity getUserFromUsername(String username){

        return userRepository
                .findByUsernameAndIsActive(username, true)
                .orElseThrow(() -> new UnmodifiableSetException("Not found user in DB"));

    }

    @Override
    public UserDetails loadUserByUsername(String username){
        UserEntity user = getUserFromUsername(username);

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.emptySet())
                .build();
    }
}
