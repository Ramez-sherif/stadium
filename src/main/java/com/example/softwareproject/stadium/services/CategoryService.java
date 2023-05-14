package com.example.softwareproject.stadium.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.models.Category;
import com.example.softwareproject.stadium.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public Category addcategory(Category c) {
        try{
            return categoryRepository.save(c);
        }catch (Exception e){
            return null;
        }

    }

    public void deleteCategory(Category c) {
        categoryRepository.delete(c);

    }
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);

    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Category GetCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category updateCategory(Long id, Category c) {
        try{
            Category exisCategory = categoryRepository.findById(id).orElseThrow();          
            exisCategory.setName(c.getName());
            exisCategory.setCapacityPercentage(c.getCapacityPercentage());
            exisCategory.setPricePercentage(c.getPricePercentage());
            return categoryRepository.save(exisCategory);
        }catch(Exception e){
            return null;
        }
        
    }

}
