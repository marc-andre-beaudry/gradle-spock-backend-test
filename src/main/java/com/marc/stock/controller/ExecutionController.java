package com.marc.stock.controller;

import org.springframework.http.ResponseEntity;

public interface ExecutionController {
    ResponseEntity<?> getExecutions();
}
