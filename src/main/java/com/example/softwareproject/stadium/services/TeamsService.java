package com.example.softwareproject.stadium.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.repositories.TeamsRepository;

@Service
public class TeamsService {
    @Autowired
private TeamsRepository teamsRepository;
}
