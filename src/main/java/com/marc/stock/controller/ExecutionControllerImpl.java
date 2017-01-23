package com.marc.stock.controller;

import com.marc.stock.domain.ExecutionBean;
import com.marc.stock.service.ExecutionService;
import com.marc.stock.ws.ExecutionWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ExecutionControllerImpl implements ExecutionController {

    @Autowired
    private ExecutionService executionService;

    @Override
    @GetMapping("/executions")
    public ResponseEntity<?> getExecutions() {
        List<ExecutionBean> beans = executionService.getExecutions();
        List<ExecutionWS> executionWSList = beans.stream().map(executionBean -> ExecutionWS.fromBean(executionBean)).collect(Collectors.toList());
        return ResponseEntity.ok(executionWSList);
    }
}
