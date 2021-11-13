package com.example.myprojectraspi.Controller;


import com.example.myprojectraspi.model.User;
import com.example.myprojectraspi.repository.UserRepository;
import com.example.myprojectraspi.security.RegistrationForm;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;

    }


// example from spring in action (not working)
//    public HttpStatus processRegistration(RegistrationForm form) {
//        userRepository.save(form.toUser(passwordEncoder));
//        return HttpStatus.CREATED;
//    }
    //register user and hashing password
@PostMapping
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
        String password = user.getPassword();
    System.out.println(password);
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    String hashedPassword = passwordEncoder.encode(password);
    System.out.println(hashedPassword);
    user.setPassword(hashedPassword);

        return userRepository.save(user);
    }



}
