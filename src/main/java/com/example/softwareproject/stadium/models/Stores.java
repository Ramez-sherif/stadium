package com.example.softwareproject.stadium.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //to let the spring know that this class will be a table in database
public class Stores {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)  
  private long id;
  private String name;
  private String location;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
