package com.marc.stock.service;

import com.marc.stock.domain.AccountBean;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Optional<AccountBean> getAccount(String uuid);
    List<AccountBean> getAccounts();
    AccountBean createAccount(AccountBean accountBean);
}
