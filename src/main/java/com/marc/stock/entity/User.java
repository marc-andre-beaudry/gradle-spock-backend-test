package com.marc.stock.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marc.stock.ws.UserWS;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, unique = true, updatable = false)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false)
    private String uuid;

    @Size(max = 50)
    @Column(name = "first_name", length = 50)
    private String firstName;

    @Size(max = 50)
    @Column(name = "last_name", length = 50)
    private String lastName;

    @Email
    @Size(max = 100)
    @Column(length = 100, unique = true)
    private String email;

    @NotNull
    @Size(min = 60, max = 60)
    @Column(name = "password_hash", length = 60)
    private String password;

    @NotNull
    @Column(nullable = false)
    private boolean activated = false;

    @Column(name = "activation_key")
    @JsonIgnore
    private String activationKey;

    @Column(name = "reset_key")
    private String resetKey;

    @Column(name = "reset_date")
    private ZonedDateTime resetDate = null;

    @Version
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_updated")
    private Date lastUpdated;

    public static User newRegisteredUser(UserWS userWS, String encodedPassword, String uuid, String activationKey) {
        User user = new User();
        user.setActivated(false);
        user.setPassword(encodedPassword);
        user.setFirstName(userWS.getFirstName());
        user.setLastName(userWS.getLastName());
        user.setUuid(uuid);
        user.setEmail(userWS.getEmail());
        user.setActivationKey(activationKey);
        return user;
    }
}
