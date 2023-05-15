package com.example.softwareproject.stadium.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.softwareproject.stadium.models.Ticket;
import com.example.softwareproject.stadium.services.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    
    @GetMapping("/reserve")
    public ModelAndView reserve()
    {
        ModelAndView view = new ModelAndView("reserveTicket");
        Ticket ticket = new Ticket();
        view.addObject("Ticket", ticket);
        return view;
    }
}
