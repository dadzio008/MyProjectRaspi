
package com.example.myprojectraspi.Controller;

import Exceptions.UsernameNotFoundException;
import com.example.myprojectraspi.model.User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class LoginController {

    @RequestMapping("/login")
    public Principal user(Principal user) {
        return user;
    }
}