package com.example.softwareproject.stadium.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.softwareproject.stadium.models.StadiumImage;

@Repository
public interface StadiumImageRepository  extends JpaRepository<StadiumImage,Long>{
    Optional<StadiumImage> findByCategoryNumber(int num);
}
