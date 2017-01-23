package com.marc.stock.service;

import com.marc.stock.domain.UserBean;

import java.util.List;
import java.util.Optional;

public interface UserService {

    UserBean signup(String email, String password);
    boolean authenticate(UserBean userBean, String providedPassword);
    boolean changePassword(UserBean userBean, String oldPassword, String newPassword);
    List<UserBean> getUsers();
    Optional<UserBean> getUserByEmail(String email);
    void activateUser(String activationKey);
    UserBean createUser(UserBean userBean);
    UserBean editUser(UserBean userBean);
    boolean deleteUser();
}
