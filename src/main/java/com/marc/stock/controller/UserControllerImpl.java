package com.marc.stock.controller;

import com.marc.stock.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;

    @Override
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @Override
    @GetMapping("/users/search")
    public ResponseEntity<?> getUserByEmail(String email) {
        return null;
    }

    @Override
    @PostMapping("/users")
    public ResponseEntity<?> createUser() {
        return null;
    }

    @Override
    public ResponseEntity<?> updateUser() {
        return null;
    }
}
