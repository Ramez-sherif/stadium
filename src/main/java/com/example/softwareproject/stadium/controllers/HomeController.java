package com.example.softwareproject.stadium.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping("/Home")
    public ModelAndView home(){
        ModelAndView home = new ModelAndView("newHome.html");
        return home;
    }

    @GetMapping("")
    public String login(){
        return "redirect:/auth/login";
    }
    @GetMapping("/register")
    public ModelAndView register(){
        ModelAndView register = new ModelAndView("register.html");
        return register;
    }
}

