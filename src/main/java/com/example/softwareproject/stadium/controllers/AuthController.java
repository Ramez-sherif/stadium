package com.example.softwareproject.stadium.controllers;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.softwareproject.stadium.models.Role;
import com.example.softwareproject.stadium.models.User;
import com.example.softwareproject.stadium.repositories.RoleRepository;
import com.example.softwareproject.stadium.repositories.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired 
    private RoleRepository roleRepository;
    @Autowired 
    private UserRepository userRepository;
    @PostMapping("register")
    public String register(@RequestBody Map<String,String> body){
        try{
            List<Role> userRole = this.roleRepository.findByName("User");
            String firstName = body.get("FirstName");
            String secondName = body.get("SecondName");
            String email = body.get("Email");
            String password = body.get("Password");
            String passwordHash = this.bCryptPasswordEncoder.encode(password);
            /*
            String firstName = user.getFirstName();
            String secondName = user.getSecondName();
            String email = user.getEmail();
            String password = user.getPassword();
            String passwordHash = this.bCryptPasswordEncoder.encode(password);
            */
            User newUser = new User();
            newUser.setFirstName(firstName);
            newUser.setSecondName(secondName);
            newUser.setEmail(email);
            newUser.setPassword(passwordHash);        
            newUser.setRole(userRole.get(0));
            this.userRepository.save(newUser);
        }catch(Exception e){
            return "redirect:/thymeleaf/register";
        }
       

        return "redirect:/Home/Home";  
    }
}
