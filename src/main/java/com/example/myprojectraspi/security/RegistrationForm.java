package com.example.myprojectraspi.security;

import com.example.myprojectraspi.model.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
//Example from spring in action(not working. figure why?)
@Data
public class RegistrationForm {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private Date birthDate;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, email, passwordEncoder.encode(password), phoneNumber, birthDate);
    }

}
