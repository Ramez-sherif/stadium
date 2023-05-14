package com.example.softwareproject.stadium.controllers;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.softwareproject.stadium.models.Category;
import com.example.softwareproject.stadium.models.Matches;
import com.example.softwareproject.stadium.models.Role;
import com.example.softwareproject.stadium.models.Stadium;
import com.example.softwareproject.stadium.models.User;
import com.example.softwareproject.stadium.repositories.RoleRepository;
import com.example.softwareproject.stadium.repositories.UserRepository;
import com.example.softwareproject.stadium.services.AuthService;
import com.example.softwareproject.stadium.services.UserService;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/register")
    public ModelAndView register(){
        ModelAndView view = new ModelAndView("register.html");
        User user = new User();
        view.addObject("User", user);
        return view;    
    }
    
    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute User user){
        ModelAndView view = new ModelAndView("login.html");
        User user2 = new User();
        view.addObject("User", user2);
        this.authService.register(user);
        return view;
    }
    
    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView view = new ModelAndView("login.html");
        User user = new User();
        view.addObject("User", user);
        return view;    
    }
    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute User user){
        if(this.authService.login(user) == null){
            ModelAndView view = new ModelAndView("login.html");
            User user2 = new User();
            view.addObject("User", user2);
            return view;
        }
        ModelAndView view2 = new ModelAndView("home.html");
        return view2;
        
    }
    @GetMapping("/test")
    public String test(){
        return "you can access this test";
    }
}
