package com.example.softwareproject.stadium.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.models.Stadium;
import com.example.softwareproject.stadium.models.StadiumImage;
import com.example.softwareproject.stadium.repositories.StadiumImageRepository;
import com.example.softwareproject.stadium.repositories.StadiumRepository;

@Service
public class StadiumImageService {
    @Autowired
    private StadiumImageRepository stadiumImageRepository;
    @Autowired
    private StadiumRepository stadiumRepository;

    public List<StadiumImage> getAllImgUrl() {

        return this.stadiumImageRepository.findAll();

    }

    public StadiumImage getStadiumById(Long id) {
        return stadiumImageRepository.findById(id).orElse(null);
    }

    public StadiumImage getImgLink(long id) {

        Stadium stadium = stadiumRepository.findById(id).orElse(null);
        if (stadium== null) return null;
        return stadium.getStadiumImage();
    }

}
