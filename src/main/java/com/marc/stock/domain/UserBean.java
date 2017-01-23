package com.marc.stock.domain;

import com.marc.stock.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserBean {
    private String email;
    private String uuid;
    private String firstName;
    private String lastName;
    private String activationKey;
    private String password;
    private boolean deleted;
    private boolean activated;

    public static UserBean fromUser(User user) {
        UserBean userBean = new UserBean();
        userBean.setUuid(user.getUuid());
        userBean.setPassword(user.getPassword());
        userBean.setFirstName(user.getFirstName());
        userBean.setLastName(user.getLastName());
        userBean.setActivated(user.isActivated());
        userBean.setActivationKey(user.getActivationKey());
        userBean.setEmail(user.getEmail());
        return userBean;
    }
}
