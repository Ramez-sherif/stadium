package com.example.softwareproject.stadium.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.models.Stadium;
import com.example.softwareproject.stadium.repositories.StadiumImageRepository;
import com.example.softwareproject.stadium.repositories.StadiumRepository;

@Service
public class StadiumService {
     @Autowired
     private StadiumRepository stadiumRepository;

     @Autowired
     private StadiumImageRepository stadiumImageRepository;

     public void addStadium(Stadium stadium) {
          stadiumRepository.save(stadium);
     }

     public void updateStadium(Long id, Stadium stadium) {
          stadiumRepository.findById(id).orElse(null);
          stadiumRepository.save(stadium);

     }

     public List<Stadium> getAllStadiums() {
          return stadiumRepository.findAll();
     }

     public Stadium getStadiumById(Long id) {
          return stadiumRepository.findById(id).orElse(null);
     }

     public void deleteStadiumById(Long id) {
          stadiumRepository.deleteById(id);

     }

     public void deleteStadium(Stadium stadium) {
          stadiumRepository.delete(stadium);
     }

}
