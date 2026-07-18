package com.springsecurity.Spring_Security.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;

@Service
public class JwtService {

    public static final String SECRET_KEY = "Mynameissaurabhkumarojhafromvaranasi";


    public String genreateToken(String username, String role){

        HashMap<String, Object> claims = new HashMap<>();
        claims.put("ROLE", role);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600*1000))
                .addClaims(claims)
                .signWith(getSignedKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public Key getSignedKey(){
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }


    public Claims verifySignatureAndExtractClaims(String token){

        return Jwts.parser()
                .setSigningKey(getSignedKey())
                .build()
                .parseClaimsJws(token).getBody();

    }

    public String extractUsername(String token){
        return verifySignatureAndExtractClaims(token).getSubject();
    }

    public Date getExpiration(String token){
        return verifySignatureAndExtractClaims(token).getExpiration();
    }

    public boolean isTokenExpired(String token){
        return getExpiration(token).before(new Date());
    }
}
