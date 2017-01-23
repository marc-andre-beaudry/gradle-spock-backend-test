package com.marc.stock.domain;

import com.marc.stock.entity.Account;
import com.marc.stock.ws.AccountWS;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class AccountBean {

    private String uuid;
    private BigDecimal balance;
    private String ownerUuid;
    private Date lastUpdated;

    public static AccountBean fromAccount(Account account) {
        AccountBean accountBean = new AccountBean();
        accountBean.setUuid(account.getUuid());
        accountBean.setOwnerUuid(account.getOwner().getUuid());
        accountBean.setBalance(account.getBalance());
        accountBean.setLastUpdated(account.getLastUpdated());
        return accountBean;
    }

    public static AccountBean fromAccountWS(AccountWS accountWS) {
        AccountBean accountBean = new AccountBean();
        accountBean.setUuid(accountWS.getUuid());
        accountBean.setOwnerUuid(accountWS.getOwnerUuid());
        accountBean.setBalance(accountWS.getBalance());
        accountBean.setLastUpdated(accountWS.getLastUpdated());
        return accountBean;
    }
}
