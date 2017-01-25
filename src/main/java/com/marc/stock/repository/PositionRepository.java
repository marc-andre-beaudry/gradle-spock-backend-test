package com.marc.stock.repository;

import com.marc.stock.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
    Position findByUuid(String uuid);
}
