package com.marc.stock.repository;

import com.marc.stock.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByUuid(String uuid);
}
