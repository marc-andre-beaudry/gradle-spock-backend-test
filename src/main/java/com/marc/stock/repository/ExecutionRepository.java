package com.marc.stock.repository;

import com.marc.stock.entity.Execution;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExecutionRepository extends JpaRepository<Execution, Long> {
    Execution findByUuid(String uuid);
}
