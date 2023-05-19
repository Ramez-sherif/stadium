package com.example.softwareproject.stadium.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.softwareproject.stadium.models.Category;
import com.example.softwareproject.stadium.models.Stadium;
import com.example.softwareproject.stadium.models.StadiumCategories;

@Repository
public interface StadiumCategoriesRepository extends JpaRepository<StadiumCategories,Long> {
List<StadiumCategories>findByStadium(Stadium stadium);  
 
 //List<Category>findByStadium(Stadium stadium);   

}
