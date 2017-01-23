package com.marc.stock.repository;


import com.marc.stock.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUuid(String uuid);
    Optional<User> findOneByEmail(String email);
    Optional<User> findOneByResetKey(String resetKey);
    Optional<User> findOneByActivationKey(String activationKey);
}
