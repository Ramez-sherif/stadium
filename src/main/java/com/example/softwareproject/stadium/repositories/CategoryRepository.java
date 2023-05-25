package com.example.softwareproject.stadium.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.softwareproject.stadium.models.Category;
import com.example.softwareproject.stadium.models.StadiumImage;

@Repository
public interface CategoryRepository extends JpaRepository<Category,String> {
    List<Category> findByStadiumImageId(Long stadiumImageId);
    List<Category> findByName(String name);
}
