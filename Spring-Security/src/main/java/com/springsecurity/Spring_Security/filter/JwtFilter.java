package com.springsecurity.Spring_Security.filter;


import com.springsecurity.Spring_Security.enums.Permission;
import com.springsecurity.Spring_Security.enums.Role;
import com.springsecurity.Spring_Security.service.JwtService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
    String token = null;
        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
        }

        if(token != null && SecurityContextHolder.getContext().getAuthentication() == null){
            Claims claims = jwtService.verifySignatureAndExtractClaims(token);

            Role role = Role.valueOf("ROLE_"+claims.get("ROLE", String.class));

            List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
            role.getPermissions().forEach(permission->{
                simpleGrantedAuthorities.add(new SimpleGrantedAuthority(permission.name()));
            });
            
            if(!jwtService.isTokenExpired(token)){
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(claims.getSubject(),null,simpleGrantedAuthorities);

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
