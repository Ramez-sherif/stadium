package com.example.softwareproject.stadium.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.softwareproject.stadium.models.Matches;
import com.example.softwareproject.stadium.models.Stadium;
import com.example.softwareproject.stadium.models.Teams;
import com.example.softwareproject.stadium.models.Tournaments;
import com.example.softwareproject.stadium.repositories.TeamsRepository;
import com.example.softwareproject.stadium.services.MatchesService;
import com.example.softwareproject.stadium.services.StadiumService;
import com.example.softwareproject.stadium.services.TeamsService;
import com.example.softwareproject.stadium.services.TournamentsService;

@RestController
@RequestMapping("/match")
public class MatchController {
    @Autowired
    private MatchesService matchesService;
    @Autowired
    private TeamsService teamsService;
    @Autowired
    private TournamentsService tournamentsService;
    @Autowired
    private StadiumService stadiumService;

@GetMapping("/add")
public ModelAndView addMatch(){
    ModelAndView view = new ModelAndView("AddMatch.html");

    List<Teams> allTeams= teamsService.getAllTeams();
    List<Tournaments> allTournaments= tournamentsService.getAllTournaments();
    List<Stadium> allStadiums=stadiumService.getAllStadiums();

        Matches matches= new Matches();
        view.addObject("matches", matches).addObject("allTeams", allTeams).addObject("allTournaments", allTournaments)
        .addObject("allStadiums", allStadiums);
 return view;      
}

    @PostMapping("/add")
    public String addMatch(@ModelAttribute Matches match)
    {
        this.matchesService.addMatch(match);
       // return this.matchesService.getAllMatches().ObjToJson();

       return "redirect:/home/home"; //redirect to home page when success
    }
    @PostMapping("/save")
    public String saveMatch(@ModelAttribute Matches matches)
    {
        matchesService.addMatch(matches);
        return "redirect:home/home";

    }
}
