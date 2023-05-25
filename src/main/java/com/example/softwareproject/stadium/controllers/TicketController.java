package com.example.softwareproject.stadium.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.mapping.Set;
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
import com.example.softwareproject.stadium.models.StadiumCategories;
import com.example.softwareproject.stadium.models.StadiumImage;
import com.example.softwareproject.stadium.models.Ticket;
import com.example.softwareproject.stadium.models.User;
import com.example.softwareproject.stadium.services.CategoryService;
import com.example.softwareproject.stadium.services.MatchesService;
import com.example.softwareproject.stadium.services.PaymentHistoryService;
import com.example.softwareproject.stadium.services.StadiumCategoriesService;
import com.example.softwareproject.stadium.services.StadiumImageService;
import com.example.softwareproject.stadium.services.StadiumService;
import com.example.softwareproject.stadium.services.TicketService;
import com.example.softwareproject.stadium.services.UserService;
import com.example.softwareproject.stadium.viewModels.ReserveTicket;
import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    @Autowired
    private UserService userService;
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
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/reserve")
    public ModelAndView reserve(@RequestParam("id") Long id)
    {
      
        Matches matches = matchesService.getMatchById(id);

        Stadium stadium=matches.getStadium();
        StadiumImage stadiumImage = stadiumImageService.getImgLink(stadium.getId());

        List<Category> allCategories =  stadiumCategoriesService.getCategoriesForStadium(matches.getStadium());
        HashMap<Category,Double> priceOfCategory = new HashMap<>();

        for(Category c:allCategories){
          double total = (c.getPricePercentage() * matches.getPrice())/100+ matches.getPrice();
          priceOfCategory.put(c,total);

        }
        ModelAndView view = new ModelAndView("ticketpage.html");
        Ticket ticket = new Ticket();
        view.addObject("Ticket", ticket)
        .addObject("priceOfCategory", priceOfCategory)
        .addObject("allCategories", allCategories)
        .addObject("stadiumImage", stadiumImage).addObject("matches", matches);
        return view;
         
    }

    @PostMapping("/reserve")
    public String reserve(@RequestParam Map<String, String> myMapp,@ModelAttribute Matches matches,@AuthenticationPrincipal UserDetails userDetails)
    {
      String email = userDetails.getUsername();
      User user = this.userService.getUserByEmail(email);
      if(user == null){
        System.out.println("user is null");
        return "refirect:/Home";
      }

      Matches thisMatch = this.matchesService.getMatchById(matches.getId());
      Stadium thisStadium = this.stadiumService.getStadiumById(thisMatch.getStadium().getId());
      if(this.ticketService.getTicketsByUser(userDetails).size() > 4){
        return "redirect:/tickets/reserve?id=" + matches.getId();
      }
      for (Entry<String, String> key :myMapp.entrySet()) {
        Category thisCategory = this.categoryService.GetCategoryById(key.getKey());
        if(thisCategory == null) continue;
        System.out.println(thisCategory.getName());
        System.out.println("Key: " + key.getKey()); 
        System.out.println("Value:" + key.getValue()); 

        Integer size = 0;
        try{
          size = Integer.parseInt(key.getValue());
        }catch(Exception e){
          size = 0;
        }

        for(int i =0; i < size ;i++){          
          Ticket ticket = new Ticket();
          ticket.setCategory(thisCategory);
          ticket.setMatch(thisMatch);
          ticket.setReservationDate(LocalDateTime.now());
          ticket.setConfirmation(false);
          double total = ((thisCategory.getPricePercentage() * thisMatch.getPrice())/100) + thisMatch.getPrice();
          ticket.setPrice(total);
          ticket.setStadium(thisStadium);
          ticket.setStore(null);
          ticket.setUser(user);
          if(this.ticketService.addTicket(ticket) == null){
            System.out.println("couldn't add ticket");
          }
        }
        System.out.println("real size = "+ size);
      }
      
      System.out.println("Match Id = " +  matches.getId());
      
        
      return "redirect:/Home";
    }
    @GetMapping("/view")
    public ModelAndView view(@AuthenticationPrincipal UserDetails userDetails)
    {
      //List<Ticket> tickets = this.ticketService.getTicketsByManager(userDetails);
      List<Ticket> tickets = this.ticketService.getTicketsByManager(userDetails);
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
      this.paymentHistoryService.ConfrimTicket(id);
      return "redirect:/tickets/view";
    }
}
