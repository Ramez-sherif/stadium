package com.example.softwareproject.stadium.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity //to let the spring know that this class will be a table in database
public class Category {
    @Id
    @GeneratedValue(generator =  "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private String id;
    private String name;
    private double capacityPercentage;
    private double pricePercentage;
    
    public Set<StadiumCategories> getStadiumCategories() {
        return stadiumCategories;
    }

    public void setStadiumCategories(Set<StadiumCategories> stadiumCategories) {
        this.stadiumCategories = stadiumCategories;
    }

    public StadiumImage getStadiumImage() {
        return stadiumImage;
    }

    public void setStadiumImage(StadiumImage stadiumImage) {
        this.stadiumImage = stadiumImage;
    }

    @OneToMany(mappedBy = "category")
    Set<StadiumCategories> stadiumCategories; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_Image_Id", referencedColumnName = "id")
    private StadiumImage stadiumImage;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
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

   
}
