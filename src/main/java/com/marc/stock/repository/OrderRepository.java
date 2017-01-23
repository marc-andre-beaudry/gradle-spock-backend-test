package com.marc.stock.repository;

import com.marc.stock.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Order findByUuid(String uuid);
}
