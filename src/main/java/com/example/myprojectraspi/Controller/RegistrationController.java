package com.example.myprojectraspi.Controller;


import com.example.myprojectraspi.model.User;
import com.example.myprojectraspi.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/register")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @PostMapping
    @CrossOrigin
    public User registerUser(@RequestBody User user) throws Exception {
        String tempEmail = user.getEmail();
        String tempLogin = user.getUsername();
        if (tempEmail != null && !"".equals(tempEmail) && tempLogin != null && !"".equals(tempLogin)) {
            User userByEmail = userRepository.findByEmail(tempEmail);
            if (userByEmail != null) {
                throw new Exception("User with email " + tempEmail + "is already exist");
            }
            User userByLogin = userRepository.findByUsername(tempLogin);
            if (userByLogin != null) {
                throw new Exception("User with login " + tempLogin + "is already exist");
            }
        }
        return userRepository.save(user);
    }

}
