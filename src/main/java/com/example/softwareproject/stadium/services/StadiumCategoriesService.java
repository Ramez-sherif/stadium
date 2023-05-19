package com.example.softwareproject.stadium.services;

import java.util.ArrayList;
import java.util.List;

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

    public StadiumCategories addCategoryToStadium(Long stadiumId,String categoryId)
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

    public List<Category> getCategoriesForStadium(Stadium stadium)
    {
       // return stadiumCategoriesRepository.findByStadium(stadium);
         List<StadiumCategories> stadiumCategoriesList = stadiumCategoriesRepository.findByStadium(stadium);
         List<Category> categoriesList = new ArrayList<>();
        for (StadiumCategories stadiumCategories : stadiumCategoriesList) {
            categoriesList.add(stadiumCategories.getCategory());
        }
        return categoriesList;
    }
}
