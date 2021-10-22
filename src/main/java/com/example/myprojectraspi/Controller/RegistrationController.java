//package com.example.myprojectraspi.Controller;
//
//
//import com.example.myprojectraspi.model.User;
//import com.example.myprojectraspi.service.RegistrationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin
//public class RegistrationController {
//
//
//    private RegistrationService registrationService;
//
//    @PostMapping("/register")
//    @CrossOrigin
//    public User registerUser(@RequestBody User user) throws Exception{
//        String tempEmail = user.getEmail();
//        if (tempEmail != null && !"".equals(tempEmail)) {
//            User userObj = registrationService.fetchUserByEmail(tempEmail);
//            if (userObj != null) {
//                throw new Exception("User with " + tempEmail + "is already exist");
//            }
//        }
//        return registrationService.saveUser(user);
//    }
//
//}
