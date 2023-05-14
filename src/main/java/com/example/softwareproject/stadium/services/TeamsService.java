package com.example.softwareproject.stadium.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.models.Teams;
import com.example.softwareproject.stadium.repositories.TeamsRepository;

@Service
public class TeamsService {
    @Autowired
private TeamsRepository teamsRepository;

public void addTeam(Teams team){
Teams teams =new Teams();
teams.setName(team.getName());
}

public List<Teams> getAllTeams() {
    return teamsRepository.findAll();
}

public Teams getTeamById(long id) {
    return teamsRepository.findById(id).orElse(null);
}

public void deleteTeam(Teams t) {
    teamsRepository.delete(t);

}
public void deleteTeamById(long id) {
    teamsRepository.deleteById(id);
}
public Teams updateTeam(Long id, Teams team) {
    try{

        Teams existeam = teamsRepository.findById(id).orElseThrow();
        existeam.setName(team.getName());
        return teamsRepository.save(existeam);
    }catch(Exception e){
        return null;
    }
}

}





