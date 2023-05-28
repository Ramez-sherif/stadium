package com.example.softwareproject.stadium.controllers;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        
        ModelAndView registerView = new ModelAndView("register.html");
        User newUser = new User();
        registerView.addObject("User", newUser);
        return registerView;    
    }
    
    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute User newUser){
        Role userRole = this.authService.getRoleByName("User");

        newUser.setRole(userRole);
        
        if(userRole != null && this.authService.register(newUser) != null){
            ModelAndView loginView = new ModelAndView("login.html");
            User loginUser = new User();
            loginView.addObject("User", loginUser);
            return loginView;
           
        }
        ModelAndView registerView = new ModelAndView("register.html");
        registerView.addObject("User", newUser);
        return registerView;
       
    }
    
    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView view = new ModelAndView("login.html");
        User user = new User();
        view.addObject("User", user);
        return view;    
    }
    @GetMapping("/login/error")
    public ModelAndView loginError(){
        String message = "Invalid Username or password";
        ModelAndView view = new ModelAndView("login.html");
        User user = new User();
        view.addObject("User", user)
        .addObject("message", message);
        return view;    
    }
    @PostMapping("/login/save")
    public String login(@ModelAttribute User user){
        //System.out.println("heeeeeeeeeeeeeeeeeeeeeeeereeeeeeeeeeeeee "  + user.getPassword() + "Email " + user.getEmail());        
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword(),user.getAuthorities());
        Authentication authenticated = this.authService.authenticate(authentication);
        SecurityContextHolder.getContext().setAuthentication(authenticated);
        return "redirect:/auth/home";
        /*
         * 
         Map<String,String> token = this.authService.login(user);
         if(token == null){
             ModelAndView view = new ModelAndView("login.html");
             User user2 = new User();
             view.addObject("User", user2);
             return view;
            }
            System.out.println(token.get("token"));
            ModelAndView view2 = new ModelAndView("home.html");
            return view2;
            
            */
        }
        @GetMapping("/home")
        public ModelAndView home(){

        ModelAndView view2 = new ModelAndView("newHome.html");
        return view2;
        
    }

        @GetMapping("/manager/register")
        public ModelAndView StoreManagerRegister(){
            ModelAndView view = new ModelAndView("StoreRegistration.html");
            User user = new User();
            view.addObject("User", user);
            return view;    
        }
        @PostMapping("/manager/register")
        public ModelAndView StoreManagerRegister(@ModelAttribute User user,@RequestParam("storeName") String storeName,@RequestParam("storeLocation") String storeLocation){
            Role role = this.authService.getRoleByName("StoreManager");
            user.setRole(role);
            User newManager = this.authService.register(user);
            
            if(role != null &&  newManager != null){
                Stores store = new Stores();
                store.setLocation(storeLocation);
                store.setName(storeName);
                store.setManager(newManager);
               // System.out.println(store.getLocation() + "store " + store.getName() + " " + store.getManager().getEmail());
                if(this.storeService.addStore(store) != null){
                    ModelAndView loginView = new ModelAndView("login.html");
                    User loginUser = new User();
                    loginView.addObject("User", loginUser);
                    return loginView;
                }            
            }
            ModelAndView registerView = new ModelAndView("StoreRegistration.html");
            User registerUser = new User();
            registerView.addObject("User", registerUser);
            return registerView;
       
    }
}
