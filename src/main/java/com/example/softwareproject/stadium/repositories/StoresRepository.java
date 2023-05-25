package com.example.softwareproject.stadium.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.softwareproject.stadium.models.Stores;

@Repository
public interface StoresRepository extends JpaRepository<Stores,Long> {
    Optional<Stores> findByManagerId(String id); 
}
