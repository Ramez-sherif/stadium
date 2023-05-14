package com.example.softwareproject.stadium.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.softwareproject.stadium.models.Matches;
import com.example.softwareproject.stadium.models.Stadium;
import com.example.softwareproject.stadium.models.Teams;
import com.example.softwareproject.stadium.models.Tournaments;
import com.example.softwareproject.stadium.services.MatchesService;
import com.example.softwareproject.stadium.services.StadiumService;
import com.example.softwareproject.stadium.services.TeamsService;
import com.example.softwareproject.stadium.services.TournamentsService;

@RequestMapping("/match")
@Controller
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
    ModelAndView view = new ModelAndView("AddMatches.html");

    List<Teams> allTeams= teamsService.getAllTeams();
    List<Tournaments> allTournaments= tournamentsService.getAllTournaments();
    List<Stadium> allStadiums=stadiumService.getAllStadiums();
        Matches matches= new Matches();
        view.addObject("Matches", matches).addObject("AllTeams", allTeams).addObject("AllTournaments", allTournaments)
        .addObject("AllStadiums", allStadiums);
         return view;      
}

    @PostMapping("/add")
    public ModelAndView addMatch(@ModelAttribute Matches match)
    {
        Stadium newStadium = match.getStadium();
        Teams newTeam1 = match.getTeam1();
        Teams newTeam2 = match.getTeam2();
        Tournaments newTournaments = match.getTournament();
        match.setStadium(this.stadiumService.getStadiumById(newStadium.getId()));
        match.setTeam1(this.teamsService.getTeamById(newTeam1.getId()));
        match.setTeam2(this.teamsService.getTeamById(newTeam2.getId()));
        match.setTournament(this.tournamentsService.getTournamentById(newTournaments.getId()));

        if(this.matchesService.addMatch(match) == null){
            ModelAndView matchView = new ModelAndView("AddMatches.html");
            List<Teams> allTeams= teamsService.getAllTeams();
            List<Tournaments> allTournaments= tournamentsService.getAllTournaments();
            List<Stadium> allStadiums=stadiumService.getAllStadiums();
            Matches matches= new Matches();
            matchView.addObject("Matches", matches).addObject("AllTeams", allTeams).addObject("AllTournaments", allTournaments)
            .addObject("AllStadiums", allStadiums);
            return matchView;
        }
        ModelAndView homeView = new ModelAndView("home.html");        
        //return this.matchesService.getAllMatches().ObjToJson();

       return homeView; //redirect to home page when success
    }
 
}
