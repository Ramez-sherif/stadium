package com.example.softwareproject.stadium.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity //to let the spring know that this class will be a table in database
public class Matches {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "team1_id", nullable = false)
    private Teams team1;

    @ManyToOne
    @JoinColumn(name = "team2_id", nullable = false)
    private Teams team2;

    @ManyToOne
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournaments tournament;

    @ManyToOne
    @JoinColumn(name = "stadium_id", nullable = false)
    private Stadium stadium;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private double price;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Teams getTeam1() {
        return this.team1;
    }

    public void setTeam1(Teams team1) {
        this.team1 = team1;
    }

    public Teams getTeam2() {
        return this.team2;
    }

    public void setTeam2(Teams team2) {
        this.team2 = team2;
    }

    public Tournaments getTournament() {
        return this.tournament;
    }

    public void setTournament(Tournaments tournament) {
        this.tournament = tournament;
    }

    public Stadium getStadium() {
        return this.stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}


   
