package com.marc.stock.service;

import com.marc.stock.domain.UserBean;

public interface MailService {
    void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml);
    void sendActivationEmail(UserBean userBean);
    void sendCreationEmail(UserBean userBean);
    void send(UserBean userBean);
}
