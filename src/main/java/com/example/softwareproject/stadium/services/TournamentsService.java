package com.example.softwareproject.stadium.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.models.Tournaments;
import com.example.softwareproject.stadium.repositories.TournamentsRepository;

@Service
public class TournamentsService {
    @Autowired
    private TournamentsRepository tournamentsRepository;

    public List<Tournaments> getAllTournaments() {
        return tournamentsRepository.findAll();
    }

    public Tournaments getTournamentById(Long id) {
        return tournamentsRepository.findById(id).orElse(null);
    }

    public Tournaments addTournament(Tournaments tournament) {
        return tournamentsRepository.save(tournament);
    }

    public Tournaments updateTournament(Long id, Tournaments newTournament) {
        Tournaments tournaments = tournamentsRepository.findById(id).orElse(null);
        if (tournaments==null) {
            return null;
        }
            
            tournaments.setName(newTournament.getName());
            return tournamentsRepository.save(tournaments);
        
    }

    public void deleteTournament(Long id) {
        tournamentsRepository.deleteById(id);
    }
}