package com.example.softwareproject.stadium.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.softwareproject.stadium.models.Category;
import com.example.softwareproject.stadium.models.Matches;
import com.example.softwareproject.stadium.models.PaymentHistory;
import com.example.softwareproject.stadium.models.Stadium;
import com.example.softwareproject.stadium.models.StadiumImage;
import com.example.softwareproject.stadium.models.Ticket;
import com.example.softwareproject.stadium.services.MatchesService;
import com.example.softwareproject.stadium.services.PaymentHistoryService;
import com.example.softwareproject.stadium.services.StadiumCategoriesService;
import com.example.softwareproject.stadium.services.StadiumImageService;
import com.example.softwareproject.stadium.services.StadiumService;
import com.example.softwareproject.stadium.services.TicketService;
import com.example.softwareproject.stadium.viewModels.ReserveTicket;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private PaymentHistoryService paymentHistoryService;
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

    @GetMapping("/reserve")
    public ModelAndView reserve(@RequestParam("id") Long id)
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
        ModelAndView view = new ModelAndView("ticketpage.html");
        Ticket ticket = new Ticket();
        view.addObject("Ticket", ticket)
        .addObject("priceOfCategory", priceOfCategory)
        .addObject("allCategories", allCategories)
        .addObject("stadiumImage", stadiumImage).addObject("matches", matches);
        return view;
        /* 
        
        List<ReserveTicket> reserveTickets = new ArrayList<ReserveTicket>();
        for(Category category : allCategories){
          
          ReserveTicket reserveTicket = new ReserveTicket();
          double total = category.getPricePercentage() * matches.getPrice()+ matches.getPrice();
          reserveTicket.setPrice(total);
          reserveTicket.setCategory(category);
          reserveTicket.setQuantity(0);
          reserveTickets.add(reserveTicket);
        }
        ModelAndView view = new ModelAndView("ticketpage.html");
        
        view.addObject("reserveTickets", reserveTickets)
        .addObject("allCategories", allCategories)
        .addObject("stadiumImage", stadiumImage)
        .addObject("matches", matches);
        return view;
        */
         
    }

    @PostMapping("/reserve")
    public ModelAndView reserve(@RequestParam Map<String, String> myMapp,@ModelAttribute Matches matches)
    {
      //for (ReserveTicket reserveTicket : reserveTickets) {
        //System.out.println(reserveTicket.getCategory().getName());        
      //}
      
      //Map<String, String> myMap = new HashMap<>();
      for (Entry<String, String> key :myMapp.entrySet()) {
        System.out.println("Key: " + key.getKey()); 
        System.out.println("Value:" + key.getValue()); 
        
      }
      System.out.println("Match Id = " +  matches.getId());
      
        
      ModelAndView home= new ModelAndView("home.html");
      return home;
    }
    @GetMapping("/view")
    public ModelAndView view()
    {
      String email = "r@gmail.com";
      //List<Ticket> tickets = this.ticketService.getTicketsByManager(userDetails);
      List<Ticket> tickets = this.ticketService.getTicketsByManagerStatic(email);
      if(tickets.size() == 0){
        System.out.println("empty");
        ModelAndView ticketView = new ModelAndView("home.html");
      return ticketView;
      }
      System.out.println("not empty");
      ModelAndView ticketView = new ModelAndView("StoreManConf.html");
      ticketView.addObject("allTickets", tickets);
      return ticketView;
    }


    @PostMapping("/confirm")
    public String confirmTicket(@RequestParam("id") Long id)
    {
      if(this.paymentHistoryService.ConfrimTicket(id) == null){
        return "redirect:/tickets/view";
      }
      return "redirect:/auth/home";
    }
}
