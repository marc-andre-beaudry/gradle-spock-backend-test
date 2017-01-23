package com.marc.stock.service;

import com.marc.stock.domain.AccountBean;
import com.marc.stock.entity.Account;
import com.marc.stock.repository.AccountRepository;
import com.marc.stock.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UniqueIdService uniqueIdService;

    @Override
    @Transactional(readOnly = true)
    public Optional<AccountBean> getAccount(String uuid) {
        Account account = accountRepository.findByUuid(uuid);
        return Optional.ofNullable(account).map(x -> AccountBean.fromAccount(account));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountBean> getAccounts() {
        List<AccountBean> accountBeans = new ArrayList<>();
        for(Account account : accountRepository.findAll()) {
            accountBeans.add(AccountBean.fromAccount(account));
        }
        return accountBeans;
    }

    @Override
    @Transactional
    public AccountBean createAccount(AccountBean accountBean) {
        Account account = new Account();
        account.setUuid(uniqueIdService.getUuid());
        account.setOwner(userRepository.findByUuid(accountBean.getOwnerUuid()));
        account.setBalance(BigDecimal.ZERO);
        Account createdAccount = accountRepository.save(account);
        return AccountBean.fromAccount(createdAccount);
    }
}
