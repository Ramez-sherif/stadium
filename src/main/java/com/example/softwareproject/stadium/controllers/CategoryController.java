package com.example.softwareproject.stadium.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.softwareproject.stadium.models.Category;
import com.example.softwareproject.stadium.services.CategoryService;

@RestController
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
    public String addCategory(@ModelAttribute Category category)
    {
        categoryService.addcategory(category);
        return "redirect:/home/home";
    }
    
}
