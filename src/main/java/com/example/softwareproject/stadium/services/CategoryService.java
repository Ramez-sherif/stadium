package com.example.softwareproject.stadium.services;

import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.models.Category;
import com.example.softwareproject.stadium.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public void addcategory(Category c) {
        categoryRepository.save(c);

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
        return categoryRepository.findById(id).orElseThrow();
    }

    public Category updateCategory(Long id, Category c) {
        Category exisCategory = categoryRepository.findById(id).orElseThrow();
        exisCategory.setName(c.getName());
        exisCategory.setCapacityPercentage(c.getCapacityPercentage());
        exisCategory.setPricePercentage(c.getPricePercentage());
        return categoryRepository.save(exisCategory);
    }

}
