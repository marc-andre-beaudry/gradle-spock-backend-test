package com.marc.stock.controller;

import com.marc.stock.ws.UserWS;
import org.springframework.http.ResponseEntity;

public interface RegistrationController {
    ResponseEntity<?> registerUser(UserWS userWS);
    ResponseEntity<?> activateUser(String key);
    ResponseEntity<?> requestPasswordReset();
    ResponseEntity<?> finishPasswordReset();
}
