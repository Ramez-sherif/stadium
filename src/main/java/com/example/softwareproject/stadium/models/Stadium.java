package com.example.softwareproject.stadium.models;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;



@Entity //to let the spring know that this class will be a table in database
public class Stadium {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  
    private String name;
    private String Capacity; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_Image_Id", referencedColumnName = "id")
    private StadiumImage stadiumImage;

    @OneToMany(mappedBy = "stadium")
    Set<StadiumCategories> stadiumCategories; 
/* 
    @ManyToMany
    @JoinTable(
        name = "stadium_category",
        joinColumns = @JoinColumn(name = "stadium_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories = new HashSet<>();
*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapacity() {
        return Capacity;
    }

    public void setCapacity(String capacity) {
        Capacity = capacity;
    }

    public StadiumImage getStadiumImage() {
        return stadiumImage;
    }

    public void setStadiumImage(StadiumImage stadiumImage) {
        this.stadiumImage = stadiumImage;
    }

    public Set<StadiumCategories> getStadiumCategories() {
        return stadiumCategories;
    }

    public void setStadiumCategories(Set<StadiumCategories> stadiumCategories) {
        this.stadiumCategories = stadiumCategories;
    }

   


    
}
