package com.marc.stock.service;

import com.marc.stock.domain.ExecutionBean;

import java.util.List;
import java.util.Optional;

public interface ExecutionService {
    Optional<ExecutionBean> getExecution(String uuid);
    List<ExecutionBean> getExecutions();
    ExecutionBean createExecution(ExecutionBean executionBean);
}
