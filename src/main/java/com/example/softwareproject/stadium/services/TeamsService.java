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
teams.setTeamName(team.getTeamName());
}

public List<Teams> getAllTeams() {
    return teamsRepository.findAll();
}

public Teams getTeamById(long id) {
    return teamsRepository.findById(id).orElseThrow();
}

public void deleteTeam(Teams t) {
    teamsRepository.delete(t);

}
public void deleteTeamById(long id) {
    teamsRepository.deleteById(id);
}
public Teams updateTeam(Long id, Teams team) {
    Teams existeam = teamsRepository.findById(id).orElseThrow();
    existeam.setTeamName(team.getTeamName());
    return teamsRepository.save(existeam);
}

}





