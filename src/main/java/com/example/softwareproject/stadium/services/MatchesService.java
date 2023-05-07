package com.example.softwareproject.stadium.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.repositories.MatchesRepository;
import com.example.softwareproject.stadium.repositories.StadiumRepository;

@Service
public class MatchesService {

    @Autowired
    private MatchesRepository matchesRepository;
   
}
