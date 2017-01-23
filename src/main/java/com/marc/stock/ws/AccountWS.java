package com.marc.stock.ws;

import com.marc.stock.domain.AccountBean;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class AccountWS {

    private String uuid;
    private BigDecimal balance;
    private String ownerUuid;
    private Date lastUpdated;

    public static AccountWS fromBean(AccountBean accountBean) {
        AccountWS accountWS = new AccountWS();
        accountWS.setUuid(accountBean.getUuid());
        accountWS.setOwnerUuid(accountBean.getOwnerUuid());
        accountWS.setBalance(accountBean.getBalance());
        accountWS.setLastUpdated(accountBean.getLastUpdated());
        return accountWS;
    }
}
