package com.marc.stock.controller;

import com.marc.stock.ws.AccountWS;
import org.springframework.http.ResponseEntity;

public interface AccountController {
    ResponseEntity<?> getAccount(String uuid);
    ResponseEntity<?> getAccounts();
    ResponseEntity<?> createAccount(AccountWS accountWS);
}
