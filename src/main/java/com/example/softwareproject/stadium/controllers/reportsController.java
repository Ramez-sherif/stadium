package com.example.softwareproject.stadium.controllers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.softwareproject.stadium.models.Matches;
import com.example.softwareproject.stadium.models.Ticket;
import com.example.softwareproject.stadium.services.MatchesService;
import com.example.softwareproject.stadium.services.TicketService;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;


@Controller
@RequestMapping("/match")

public class reportsController {

    @Autowired
    private MatchesService matchesService;
    @Autowired
    private TicketService ticketService;
    @GetMapping("/graph")
    public ModelAndView showGraph( @RequestParam("id") Long id) {
        ModelAndView view = new ModelAndView("reports.html");
        Matches matches= this.matchesService.getMatchById(id);
        List<Ticket> allTickets = this.ticketService.getTicketByMatchId(id);

        List<Ticket> payedList = new ArrayList();
        List<Ticket> reservedList= new ArrayList();

        for (Ticket ticket : allTickets)
        {
            if(ticket.getConfirmation().equalsIgnoreCase("payed"))
            {
                payedList.add(ticket);         
            }
            else if(ticket.getConfirmation().equalsIgnoreCase("Reserve"))
            {
                reservedList.add(ticket);
            }
        }
        double payedPercenrage = ((double)payedList.size() / allTickets.size())*100; 
        double reservedPercenrage = ((double)reservedList.size()/allTickets.size())*100;
        List<String> labels = Arrays.asList("payed","Reserved");
        List<Double> data = Arrays.asList(payedPercenrage, reservedPercenrage);
        view.addObject("labels", labels).addObject("data", data).addObject("label","data");
        return view;
    }    
}
