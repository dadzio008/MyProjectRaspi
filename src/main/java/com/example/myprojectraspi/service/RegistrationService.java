//package com.example.myprojectraspi.service;
//
//
//import com.example.myprojectraspi.model.User;
//import com.example.myprojectraspi.repository.RegistrationRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class RegistrationService {
//
//
//    private RegistrationRepository registrationRepository;
//    public User saveUser(User user){
//
//        return registrationRepository.save(user);
//    }
//    public User fetchUserByEmail(String email) {
//
//        return registrationRepository.findByEmail(email);
//
//    }
//    public User fetchUserByLoginAndPassword(String login, String password) {
//        return registrationRepository.findByLoginAndPassword(login, password);
//    }
//
//}
