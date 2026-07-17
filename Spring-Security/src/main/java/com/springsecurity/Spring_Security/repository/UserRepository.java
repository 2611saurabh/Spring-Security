package com.springsecurity.Spring_Security.repository;

import com.springsecurity.Spring_Security.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    Optional<UserEntity> findByUsernameAndIsActive(String username, boolean isActive);
}
