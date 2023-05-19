package com.example.softwareproject.stadium.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.softwareproject.stadium.models.Role;
import com.example.softwareproject.stadium.models.Stores;
import com.example.softwareproject.stadium.models.User;
import com.example.softwareproject.stadium.services.AuthService;
import com.example.softwareproject.stadium.services.StoreService;

@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private StoreService storeService;

    @GetMapping("/register")
    public ModelAndView register(){
        
        ModelAndView view = new ModelAndView("register.html");
        User user = new User();
        view.addObject("User", user);
        return view;    
    }
    
    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute User user){
        Role role = this.authService.getRoleByName("User");

        user.setRole(role);
        
        if(role != null && this.authService.register(user) != null){
            ModelAndView loginView = new ModelAndView("login.html");
            User loginUser = new User();
            loginView.addObject("User", loginUser);
            return loginView;
           
        }
        ModelAndView registerView = new ModelAndView("register.html");
        User registerUser = new User();
        registerView.addObject("User", registerUser);
        return registerView;
       
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
        @GetMapping("/manager/register")
        public ModelAndView StoreManagerRegister(){
            ModelAndView view = new ModelAndView("register.html");
            User user = new User();
            view.addObject("User", user);
            return view;    
        }
        @PostMapping("/manager/register")
        public ModelAndView StoreManagerRegister(@ModelAttribute User user,@RequestParam String storeName,String storeLocation){
            Role role = this.authService.getRoleByName("StoreManager");
            user.setRole(role);
            User newManager = this.authService.register(user);
            
            if(role != null &&  newManager != null){
                Stores store = new Stores();
                store.setLocation(storeLocation);
                store.setName(storeName);
                store.setManager(newManager);
                if(this.storeService.addStore(store) != null){
                    ModelAndView loginView = new ModelAndView("login.html");
                    User loginUser = new User();
                    loginView.addObject("User", loginUser);
                    return loginView;
                }            
            }
            ModelAndView registerView = new ModelAndView("register.html");
            User registerUser = new User();
            registerView.addObject("User", registerUser);
            return registerView;
       
    }
}
