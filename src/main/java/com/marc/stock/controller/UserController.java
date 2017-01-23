package com.marc.stock.controller;

import org.springframework.http.ResponseEntity;

public interface UserController {

    ResponseEntity<?> getAllUsers();
    ResponseEntity<?> getUserByEmail(String email);
    ResponseEntity<?> createUser();
    ResponseEntity<?> updateUser();
}
