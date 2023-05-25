package com.example.softwareproject.stadium.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.softwareproject.stadium.models.Category;
import com.example.softwareproject.stadium.models.StadiumImage;
import com.example.softwareproject.stadium.services.CategoryService;
import com.example.softwareproject.stadium.services.StadiumImageService;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private StadiumImageService stadiumImageService;
    @GetMapping("/add")
    public ModelAndView addCategory()
    {
        List<StadiumImage> stadiumImages = this.stadiumImageService.getAllImgUrl();
        ModelAndView view=new ModelAndView("AddCategory.html");
        Category category=new Category();
        view.addObject("category", category)
        .addObject("allStadiumImages", stadiumImages);
        return view;
    }
    @PostMapping("/add")
    public ModelAndView addCategory(@ModelAttribute Category category)
    {
        Category test = new Category();
        StadiumImage stadiumImage = this.stadiumImageService.getStadiumById(category.getStadiumImage().getId());
        test.setStadiumImage(stadiumImage);
        test.setName(category.getName());
        test.setCapacityPercentage(category.getCapacityPercentage());
        test.setPricePercentage(category.getPricePercentage());
        if(categoryService.addcategory(category)== null){
            ModelAndView categoyView = new ModelAndView("AddCategory.html");
            return categoyView;
        }
        ModelAndView homeView = new ModelAndView("home.html");
        return homeView;
    }
    
}
