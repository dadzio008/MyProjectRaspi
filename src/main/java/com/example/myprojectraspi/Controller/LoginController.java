package com.example.myprojectraspi.Controller;

import com.example.myprojectraspi.model.User;
import com.example.myprojectraspi.service.RegistrationService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

//        private RegistrationService registrationService;
//
//        @PostMapping("/login")
//
//        public User loginUser(@RequestBody User user)  throws Exception {
//        String login = user.getLogin();
//        String password = user.getPassword();
//        User userObj =null;
//        if (login != null && password != null) {
//              userObj  = registrationService.fetchUserByLoginAndPassword(login, password);
//
//        }
//        if (userObj == null) {
//                throw new Exception("Bad credentials");
//        }
//        return userObj;
//        }
}