package com.example.softwareproject.stadium.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.example.softwareproject.stadium.services.StoreService;

@Controller
public class StoreController {
    @Autowired
    private StoreService service;
}
