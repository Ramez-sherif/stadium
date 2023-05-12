package com.example.softwareproject.stadium.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.softwareproject.stadium.models.Matches;
import com.example.softwareproject.stadium.models.Teams;
import com.example.softwareproject.stadium.models.Tournaments;

@Repository
public interface MatchesRepository extends JpaRepository<Matches,Long> {
    List<Matches>findByTeam1OrTeam2(Teams team1,Teams team2);
    List<Matches>findByTournment(Tournaments tournaments);
}
