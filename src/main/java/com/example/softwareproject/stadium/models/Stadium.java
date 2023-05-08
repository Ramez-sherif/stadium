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

    public String getCapacity() {
        return this.Capacity;
    }

    public void setCapacity(String Capacity) {
        this.Capacity = Capacity;
    }

    public Set<StadiumCategories> getCategories() {
        return this.stadiumCategories;
    }

    public void setCategories(Set<StadiumCategories> stadiumCategories) {
        this.stadiumCategories = stadiumCategories;
    }


    
}
