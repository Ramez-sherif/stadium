package com.example.softwareproject.stadium.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.softwareproject.stadium.services.UserService;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
        
}
