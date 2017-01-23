package com.marc.stock.repository;

import com.marc.stock.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    Account findByUuid(String uuid);
}
