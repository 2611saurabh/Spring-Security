package com.springsecurity.Spring_Security.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "is_active")
    private Boolean isActive;   // ✅ lowercase i


    public UserEntity(Integer id, String username, String password, Boolean isActive) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
    }


    public UserEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getIsActive() {      // ✅ matches field name
        return isActive;
    }

    public void setIsActive(Boolean isActive) {   // ✅ matches field name
        this.isActive = isActive;
    }
}