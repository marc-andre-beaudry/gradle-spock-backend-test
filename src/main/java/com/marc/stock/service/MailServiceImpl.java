package com.marc.stock.service;

import com.marc.stock.domain.UserBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

import java.util.Locale;

@Service
@Slf4j
public class MailServiceImpl implements MailService {

    private static final String USER = "user";
    private static final String BASE_URL = "baseUrl";

    //@Autowired
    //private JavaMailSenderImpl javaMailSender;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void sendEmail(String to, String subject, String content, boolean isMultipart, boolean isHtml) {
        log.debug(content);
        /*
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, isMultipart);
            message.setTo(to);
            message.setFrom("admin@localhost");
            message.setSubject(subject);
            message.setText(content, isHtml);
            //javaMailSender.send(mimeMessage);
            log.debug("Sent e-mail to User '{}'", to);
        } catch (Exception e) {
            log.error("E-mail could not be sent to user '{}'", to, e);
        }*/
    }

    @Override
    public void sendActivationEmail(UserBean userBean) {
        log.debug("Sending activation e-mail to '{}'", userBean.getEmail());
        log.debug("token {}", userBean.getActivationKey());
        //Locale locale = Locale.getDefault();
        //Context context = new Context();
        //context.setVariable(USER, userBean);
        //context.setVariable(BASE_URL, "http://locahost:9001/");
        //String content = templateEngine.process("activationEmail", context);
        //String subject = messageSource.getMessage("email.activation.title", null, locale);
        //sendEmail(userBean.getEmail(), subject, content, false, true);
    }

    @Override
    public void sendCreationEmail(UserBean userBean) {
        log.debug("Sending creation e-mail to '{}'", userBean.getEmail());
        Locale locale = Locale.getDefault();
        Context context = new Context(locale);
        context.setVariable(USER, userBean);
        context.setVariable(BASE_URL, "http://locahost:9001/");
        String content = templateEngine.process("creationEmail", context);
        String subject = messageSource.getMessage("email.activation.title", null, locale);
        sendEmail(userBean.getEmail(), subject, content, false, true);
    }

    @Override
    public void send(UserBean userBean) {
        log.debug("Sending password reset e-mail to '{}'", userBean.getEmail());
        Locale locale = Locale.getDefault();
        Context context = new Context(locale);
        context.setVariable(USER, userBean);
        context.setVariable(BASE_URL, "http://locahost:9001/");
        String content = templateEngine.process("passwordResetEmail", context);
        String subject = messageSource.getMessage("email.reset.title", null, locale);
        sendEmail(userBean.getEmail(), subject, content, false, true);

    }
}
