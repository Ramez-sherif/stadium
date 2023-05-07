package com.example.softwareproject.stadium.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.repositories.TournamentsRepository;

@Service
public class TournamentsService {
    @Autowired
    private TournamentsRepository tournamentsRepository;
}
