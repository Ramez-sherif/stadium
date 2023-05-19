package com.example.softwareproject.stadium.controllers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.softwareproject.stadium.models.Category;
import com.example.softwareproject.stadium.models.Matches;
import com.example.softwareproject.stadium.models.Stadium;
import com.example.softwareproject.stadium.models.StadiumImage;
import com.example.softwareproject.stadium.models.Ticket;
import com.example.softwareproject.stadium.services.MatchesService;
import com.example.softwareproject.stadium.services.StadiumCategoriesService;
import com.example.softwareproject.stadium.services.StadiumImageService;
import com.example.softwareproject.stadium.services.StadiumService;
import com.example.softwareproject.stadium.services.TicketService;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;
    @Autowired
    private StadiumCategoriesService stadiumCategoriesService;
    @Autowired
    private StadiumService stadiumService;
    @Autowired
    private MatchesService matchesService;
    @Autowired
    private StadiumImageService stadiumImageService;

    @GetMapping("/reserve/{id}")
    public ModelAndView reserve(@PathVariable("id") Long id)
    {
        Matches matches = matchesService.getMatchById(id);
        
        Stadium stadium=matches.getStadium();
        StadiumImage stadiumImage = stadiumImageService.getImgLink(stadium.getId());

        List<Category> allCategories =  stadiumCategoriesService.getCategoriesForStadium(  matches.getStadium());
        HashMap<String,Double> priceOfCategory = new HashMap<>();

        for(Category c:allCategories){
          double total = c.getPricePercentage() * matches.getPrice()+ matches.getPrice();
          priceOfCategory.put(c.getName(),total);
      
        }

        ModelAndView view = new ModelAndView("new.html");
        Ticket ticket = new Ticket();
        view.addObject("Ticket", ticket)
        .addObject("allCategories", allCategories)
        .addObject("priceOfCategory", priceOfCategory)
        .addObject("stadiumImage", stadiumImage);
        return view;
    }

    @PostMapping("/reserve")
    public ModelAndView reserve(@ModelAttribute Ticket ticket)
    {
        if( ticketService.addTicket(ticket)== null)
        {
            ModelAndView reserveView = new ModelAndView("reserveTicket.html");
        
            reserveView.addObject("Ticket", ticket);
            return reserveView;
        }
        
      ModelAndView home= new ModelAndView("home.html");
      return home;
    }

}
