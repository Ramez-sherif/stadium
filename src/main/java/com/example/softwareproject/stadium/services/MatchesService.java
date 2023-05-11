package com.example.softwareproject.stadium.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.models.Matches;
import com.example.softwareproject.stadium.repositories.MatchesRepository;

@Service
public class MatchesService {

    @Autowired
    private MatchesRepository matchesRepository;
   
    public List<Matches> getAllMatches() {
        return matchesRepository.findAll();
    }

    public Matches getMatchById(Long id) {
        return matchesRepository.findById(id).orElse(null);
    }

    public Matches createNewMatch(Matches match) {
        return matchesRepository.save(match);
    }

    public void deleteMatchById(Long id) {
        matchesRepository.deleteById(id);
    }

    public Matches updateMatch(Long id,Matches match) {
         matchesRepository.findById(id).orElseThrow();
         
        return matchesRepository.save(match);
    }
/*
 
 public List<Matches> getMatchesByTeam(Teams team) {
     return matchesRepository.findByTeam1OrTeam2(team, team);
    }
    
    public List<Matches> getMatchesByTournament(Tournaments tournament) {
        return matchesRepository.findByTournament(tournament);
    }
    
    public List<Matches> getMatchesByDate(Date date) {
        return matchesRepository.findByDate(date);
    }
    */
    
}