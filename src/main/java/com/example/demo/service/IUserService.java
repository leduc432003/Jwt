package com.example.demo.service;

import com.example.demo.model.User;

import java.util.Optional;
public interface IUserService {
    Optional<User> findByUsername(String username); // Tim kim User co ton tai trong db khong?
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    User save(User user);
}
