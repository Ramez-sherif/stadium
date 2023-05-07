package com.example.softwareproject.stadium.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.softwareproject.stadium.models.Stadium;

@Repository
public interface StadiumRepository extends JpaRepository<Stadium,Long> {
    
}
