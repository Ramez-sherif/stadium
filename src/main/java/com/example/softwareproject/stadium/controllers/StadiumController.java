package com.example.softwareproject.stadium.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.softwareproject.stadium.models.Category;
import com.example.softwareproject.stadium.models.Stadium;
import com.example.softwareproject.stadium.services.CategoryService;
import com.example.softwareproject.stadium.services.StadiumService;
//@Controller
@RestController
@RequestMapping("/stadium")
public class StadiumController {
    @Autowired
    private StadiumService stadiumService;
    @Autowired
    private CategoryService categoryService;
   

    @GetMapping("/add")
    public ModelAndView addStadium(){
        List<Category> allCategories = this.categoryService.getAllCategories();       
        ModelAndView view = new ModelAndView("AddStadium.html");
        Stadium stadium= new Stadium();
        view.addObject("allCategories", allCategories)
        .addObject("stadium",stadium );

        return view;
    }
    @PostMapping("/add")
    public String addStadium(@ModelAttribute Stadium stadium){
        this.stadiumService.addStadium(stadium);
        return "redirect:home/home"; //redirect to specific page
    }
    
   

}
