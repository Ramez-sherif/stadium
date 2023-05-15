package com.example.softwareproject.stadium.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
 import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.softwareproject.stadium.models.Stadium;
import com.example.softwareproject.stadium.models.StadiumImage;
import com.example.softwareproject.stadium.services.CategoryService;
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
   

    @GetMapping("/add")
    public ModelAndView addStadium(){
        List<StadiumImage> allStadiumImage = this.stadiumImageService.getAllImgUrl();
        ModelAndView view = new ModelAndView("AddStadium.html");
        Stadium stadium= new Stadium();
        view.addObject("allStadiumImages", allStadiumImage)
        .addObject("stadium",stadium );

        return view;
    }
    @PostMapping("/add")
    public String addStadium(@ModelAttribute Stadium stadium){
        this.stadiumService.addStadium(stadium);
        return "redirect:home/home"; //redirect to specific page
    }
    
   

}
