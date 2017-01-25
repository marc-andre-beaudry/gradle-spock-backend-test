package com.marc.stock.repository;

import com.marc.stock.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUuid(String uuid);
}
