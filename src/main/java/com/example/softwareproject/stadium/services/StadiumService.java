package com.example.softwareproject.stadium.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.repositories.StadiumImageRepository;
import com.example.softwareproject.stadium.repositories.StadiumRepository;

@Service
public class StadiumService {
     @Autowired 
     private StadiumRepository stadiumRepository;

     @Autowired
     private StadiumImageRepository stadiumImageRepository;
}
