package com.example.softwareproject.stadium.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.models.Category;
import com.example.softwareproject.stadium.models.Stadium;
import com.example.softwareproject.stadium.models.StadiumCategories;
import com.example.softwareproject.stadium.repositories.CategoryRepository;
import com.example.softwareproject.stadium.repositories.StadiumCategoriesRepository;
import com.example.softwareproject.stadium.repositories.StadiumRepository;

@Service
public class StadiumCategoriesService {
    @Autowired
    private StadiumCategoriesRepository stadiumCategoriesRepository;
    private StadiumRepository stadiumRepository;
    private CategoryRepository categoryRepository;

    public StadiumCategories addCategoryToStadium(Long stadiumId,Long categoryId)
    {
        try{
            
            StadiumCategories stadiumCategories= new StadiumCategories();
            Stadium stadium=stadiumRepository.findById(stadiumId).orElseThrow();
            Category category=categoryRepository.findById(categoryId).orElseThrow();                   
            stadiumCategories.setStadium(stadium);
            stadiumCategories.setCategory(category);
            return stadiumCategoriesRepository.save(stadiumCategories);
        }
        catch(Exception e){
            return null;
        }
      
        
    }
}
/*
 @Service
public class StadiumCategoriesService {

    @Autowired
    private StadiumCategoriesRepository stadiumCategoriesRepository;

    @Autowired
    private StadiumRepository stadiumRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public StadiumCategories addCategoryToStadium(Long stadiumId, Long categoryId) {
        Stadium stadium = stadiumRepository.findById(stadiumId)
                .orElseThrow(() -> new RuntimeException("Stadium not found"));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        StadiumCategories stadiumCategories = new StadiumCategories();
        stadiumCategories.setStadium(stadium);
        stadiumCategories.setCategory(category);

        return stadiumCategoriesRepository.save(stadiumCategories);
    }
}
 */