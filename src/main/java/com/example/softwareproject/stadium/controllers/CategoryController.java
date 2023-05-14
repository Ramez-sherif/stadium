package com.example.softwareproject.stadium.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.softwareproject.stadium.models.Category;
import com.example.softwareproject.stadium.services.CategoryService;

@Controller
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("/add")
    public ModelAndView addCAtegory()
    {
        ModelAndView view=new ModelAndView("AddCategory.html");
        Category category=new Category();
        view.addObject("category", category);
        return view;
    }
    @PostMapping("/add")
    public ModelAndView addCategory(@ModelAttribute Category category)
    {
        Category test = new Category();
        test.setName(category.getName());
        test.setCapacityPercentage(category.getCapacityPercentage());
        test.setPricePercentage(category.getPricePercentage());
        System.out.println(test.getName());
        System.out.println(test.getCapacityPercentage());
        System.out.println(test.getPricePercentage());
        System.out.println("as;djkfhalkjsdfkajlsdhfkasjhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
        if(categoryService.addcategory(category)== null){
            ModelAndView categoyView = new ModelAndView("category.html");
            return categoyView;
        }
        ModelAndView homeView = new ModelAndView("home.html");
        return homeView;
    }
    
}
