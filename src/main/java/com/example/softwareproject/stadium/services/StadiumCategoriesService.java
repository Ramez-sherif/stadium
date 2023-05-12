package com.example.softwareproject.stadium.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.repositories.StadiumCategoriesRepository;

@Service
public class StadiumCategoriesService {
    @Autowired
    private StadiumCategoriesRepository stadiumCategoriesRepository;
}
