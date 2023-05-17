package com.example.softwareproject.stadium.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.models.StadiumImage;
import com.example.softwareproject.stadium.repositories.StadiumImageRepository;

@Service
public class StadiumImageService {
    @Autowired
    private StadiumImageRepository stadiumImageRepository;
    

    public  List<StadiumImage>getAllImgUrl(){
     return stadiumImageRepository.findAll(); 

    }   
    public  StadiumImage getStadiumById(Long id){
        return stadiumImageRepository.findById(id).orElse(null);
       }   
       
    //public StadiumImage getStadiumImageByNumber(){
    //    return this.stadiumImageRepository..
   // }
}
