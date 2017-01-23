package com.marc.stock.controller;

import com.marc.stock.domain.AccountBean;
import com.marc.stock.service.AccountService;
import com.marc.stock.ws.AccountWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountControllerImpl implements AccountController {

    @Autowired
    private AccountService accountService;

    @Override
    @GetMapping("/accounts/{uuid}")
    public ResponseEntity<?> getAccount(@PathVariable String uuid) {
        Optional<AccountBean> accountBeanOptional = accountService.getAccount(uuid);
        if (!accountBeanOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(AccountWS.fromBean(accountBeanOptional.get()));
    }

    @Override
    @GetMapping("/accounts")
    public ResponseEntity<?> getAccounts() {
        List<AccountBean> accountBeans = accountService.getAccounts();
        List<AccountWS> accounts = accountBeans.stream().map(accountBean -> AccountWS.fromBean(accountBean)).collect(Collectors.toList());
        return ResponseEntity.ok(accounts);
    }

    @Override
    @PostMapping("/accounts")
    public ResponseEntity<?> createAccount(@Valid @RequestBody AccountWS accountWS) {
        AccountBean accountBean = AccountBean.fromAccountWS(accountWS);
        accountService.createAccount(accountBean);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
