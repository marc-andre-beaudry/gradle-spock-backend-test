package com.marc.stock.service;

import com.marc.stock.domain.UserBean;
import com.marc.stock.entity.User;
import com.marc.stock.repository.UserRepository;
import com.marc.stock.service.exception.ActivationKeyNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UniqueIdService uniqueIdService;

    @Override
    @Transactional
    public UserBean signup(String email, String password) {
        return null;
    }

    @Override
    @Transactional
    public boolean authenticate(UserBean userBean, String providedPassword) {
        return false;
    }

    @Override
    @Transactional
    public boolean changePassword(UserBean userBean, String oldPassword, String newPassword) {
        return false;
    }

    @Override
    @Transactional
    public void activateUser(String activationKey) {
        Optional<User> userOptional = userRepository.findOneByActivationKey(activationKey);
        if (userOptional.isPresent()) {
            userOptional.ifPresent(user -> {
                user.setActivated(true);
                user.setActivationKey(null);
            });
        } else {
            throw new ActivationKeyNotFoundException();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserBean> getUsers() {
        List<UserBean> userBeans = new ArrayList<>();
        for(User user : userRepository.findAll()) {
            userBeans.add(UserBean.fromUser(user));
        }
        return userBeans;
    }

    @Override
    @Transactional
    public Optional<UserBean> getUserByEmail(String email) {
        return userRepository.findOneByEmail(email).map(user -> UserBean.fromUser(user));
    }

    @Override
    @Transactional
    public UserBean createUser(UserBean userBean) {
        return null;
    }

    @Override
    @Transactional
    public UserBean editUser(UserBean userBean) {
        return null;
    }

    @Override
    @Transactional
    public boolean deleteUser() {
        return false;
    }
}
