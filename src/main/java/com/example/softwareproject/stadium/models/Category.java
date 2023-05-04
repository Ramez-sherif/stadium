package com.example.softwareproject.stadium.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity //to let the spring know that this class will be a table in database
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double capacityPercentage;
    private double pricePercentage;

    @ManyToOne
    @JoinColumn(name="stadium_id", nullable=false)
    private Stadium stadium;


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCapacityPercentage() {
        return this.capacityPercentage;
    }

    public void setCapacityPercentage(double capacityPercentage) {
        this.capacityPercentage = capacityPercentage;
    }

    public double getPricePercentage() {
        return this.pricePercentage;
    }

    public void setPricePercentage(double pricePercentage) {
        this.pricePercentage = pricePercentage;
    }

    public Stadium getStadium() {
        return this.stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

   
}
