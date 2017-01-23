package com.marc.stock.service;

import com.marc.stock.domain.ExecutionBean;
import com.marc.stock.entity.Execution;
import com.marc.stock.repository.ExecutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExecutionServiceImpl implements ExecutionService {

    @Autowired
    private ExecutionRepository executionRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<ExecutionBean> getExecution(String uuid) {
        Execution execution = executionRepository.findByUuid(uuid);
        return Optional.ofNullable(execution).map(x -> ExecutionBean.fromExecution(execution));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExecutionBean> getExecutions() {
        List<ExecutionBean> executionBeanList = new ArrayList<>();
        for (Execution execution : executionRepository.findAll()) {
            executionBeanList.add(ExecutionBean.fromExecution(execution));
        }
        return executionBeanList;
    }

    @Override
    @Transactional
    public ExecutionBean createExecution(ExecutionBean executionBean) {
        return null;
    }
}
