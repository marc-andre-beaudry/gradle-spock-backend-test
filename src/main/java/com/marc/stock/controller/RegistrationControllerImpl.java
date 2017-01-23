package com.marc.stock.controller;

import com.marc.stock.domain.UserBean;
import com.marc.stock.entity.User;
import com.marc.stock.repository.UserRepository;
import com.marc.stock.service.MailService;
import com.marc.stock.service.UniqueIdService;
import com.marc.stock.ws.UserWS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class RegistrationControllerImpl implements RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private UniqueIdService uniqueIdService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @PostMapping("/registration")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserWS userWS) {

        if (userRepository.findOneByEmail(userWS.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("");
        }

        String encodedPassword = passwordEncoder.encode(userWS.getPassword());
        User user = User.newRegisteredUser(userWS, encodedPassword, uniqueIdService.getUuid(), uniqueIdService.getUuid());
        User createdUser = userRepository.save(user);

        UserBean userBean = UserBean.fromUser(createdUser);
        mailService.sendActivationEmail(userBean);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    @GetMapping("/registration/activate")
    public ResponseEntity<?> activateUser(@RequestParam(value = "key") String key) {
        return null;
    }

    @Override
    public ResponseEntity<?> requestPasswordReset() {
        return null;
    }

    @Override
    public ResponseEntity<?> finishPasswordReset() {
        return null;
    }
}
