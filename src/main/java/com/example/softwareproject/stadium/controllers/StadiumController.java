package com.example.softwareproject.stadium.controllers;

import java.util.List;

import javax.persistence.Id;
import javax.swing.text.View;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.softwareproject.stadium.models.Category;
import com.example.softwareproject.stadium.models.Stadium;
import com.example.softwareproject.stadium.models.StadiumImage;
import com.example.softwareproject.stadium.services.CategoryService;
import com.example.softwareproject.stadium.services.StadiumCategoriesService;
import com.example.softwareproject.stadium.services.StadiumImageService;
import com.example.softwareproject.stadium.services.StadiumService;
//@Controller
@Controller
@RequestMapping("/stadium")
public class StadiumController {
    @Autowired
    private StadiumService stadiumService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private StadiumImageService stadiumImageService;
    @Autowired
    private StadiumCategoriesService stadiumCategoriesService;
   

    @GetMapping("/add")
    public ModelAndView addStadium(){
        ModelAndView addStadiumView = new ModelAndView("AddStadium.html");
        List<StadiumImage> allStadiumImage = this.stadiumImageService.getAllImgUrl();
        List<Category> categories = this.categoryService.getAllCategories();
        Stadium stadium= new Stadium();
        addStadiumView.addObject("allStadiumImages", allStadiumImage)
        .addObject("allCategories", categories)
        .addObject("stadium",stadium );
       

        return addStadiumView;
    }
    @PostMapping("/add")
    public ModelAndView addStadium(@ModelAttribute Stadium stadium){
        
       
        StadiumImage SI = stadium.getStadiumImage();
        if(SI.getId() != null){           
            stadium.setStadiumImage(this.stadiumImageService.getStadiumById(SI.getId()));
            if(this.stadiumService.addStadium(stadium) != null){
                ModelAndView homeView = new ModelAndView("home.html");
                return homeView;
            }
        } 
        
        ModelAndView addStadiumView = new ModelAndView("AddStadium.html");
        List<StadiumImage> allStadiumImage = this.stadiumImageService.getAllImgUrl();
        List<Category> categories = this.categoryService.getAllCategories();
        Stadium stadiums= new Stadium();
        addStadiumView.addObject("allStadiumImages", allStadiumImage)
        .addObject("allCategories", categories)
        .addObject("stadium",stadiums );
        return addStadiumView;
        


        
        
            
           
                
    }
}
