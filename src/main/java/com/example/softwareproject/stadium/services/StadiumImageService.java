package com.example.softwareproject.stadium.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.models.StadiumImage;

@Service
public class StadiumImageService {
    @Autowired
    private StadiumImageService stadiumImageService;
    
    public  List<StadiumImage>getAllImgUrl(){
     return stadiumImageService.getAllImgUrl(); 
    }   
}
