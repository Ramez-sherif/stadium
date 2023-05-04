package com.example.softwareproject.stadium.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity  //to let the spring know that this class will be a table in database
public class Tournaments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
    private  String name;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTournamentName() {
        return this.name;
    }

    public void setTournamentName(String tournamentName) {
        this.name = tournamentName;
    }

}
