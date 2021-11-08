package com.example.myprojectraspi.Controller;

import com.example.myprojectraspi.model.User;
import com.example.myprojectraspi.service.RegistrationService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

        private RegistrationService registrationService;

        @PostMapping("/login")
        @ResponseBody
        public User loginUser(@RequestBody User user)  throws Exception {
                ServiceResponse response = userService.login(requestUser);
                return response;
}