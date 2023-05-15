package com.example.softwareproject.stadium.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.softwareproject.stadium.models.Tournaments;
import com.example.softwareproject.stadium.services.TournamentsService;

@Controller
@RequestMapping("/tournament")
public class TournamentsController {
    @Autowired
    private TournamentsService tournamentsService;

    @GetMapping("")
    public ModelAndView addTournament(){
        ModelAndView view = new ModelAndView("AddTournament.html");
        Tournaments tournaments= new Tournaments();
        view.addObject("Tournament", tournaments);
        return view;
    }

    @PostMapping("")
    public ModelAndView addTournament(@ModelAttribute Tournaments tournaments){
        
            if(tournamentsService.addTournament(tournaments)== null)
            {
                ModelAndView view = new ModelAndView("AddTournament.html");
                Tournaments tournamentss= new Tournaments();
                view.addObject("Tournament", tournamentss);
                return view;
            }
        
        ModelAndView homeView = new ModelAndView("home.html");
        return homeView;
    }

}
