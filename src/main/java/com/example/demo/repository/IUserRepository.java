package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String name); // Tim kim User co ton tai trong db khong?
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
