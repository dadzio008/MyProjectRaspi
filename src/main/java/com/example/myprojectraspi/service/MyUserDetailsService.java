//package com.example.myprojectraspi.service;
//
//import com.example.myprojectraspi.model.MyUserDetails;
//import com.example.myprojectraspi.model.User;
//import com.example.myprojectraspi.repository.RegistrationRepository;
//import com.example.myprojectraspi.repository.UserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//
//
//    UserRepository userRepository;
//
//    public UserDetails loadUserByUsername(String login)
//            throws UsernameNotFoundException {
//        User user = userRepository.getUserByLogin(login);
//
//        if (user == null) {
//            throw new UsernameNotFoundException("Could not find user");
//        }
//
//        return new MyUserDetails(user);
//    }
//}
