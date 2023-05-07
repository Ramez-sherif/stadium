package com.example.softwareproject.stadium.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.repositories.StoresRepository;

@Service
public class StoreService {
    @Autowired
    private StoresRepository storesRepository;
}
