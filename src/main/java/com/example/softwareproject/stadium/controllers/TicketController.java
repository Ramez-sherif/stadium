package com.example.softwareproject.stadium.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import com.example.softwareproject.stadium.services.TicketService;

@Controller
public class TicketController {
    @Autowired
    private TicketService ticketService;
}
