package com.marc.stock.repository;

import com.marc.stock.entity.Execution;
import org.springframework.data.repository.CrudRepository;

public interface ExecutionRepository extends CrudRepository<Execution, Long> {
    Execution findByUuid(String uuid);
}
