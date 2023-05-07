package com.example.softwareproject.stadium.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.softwareproject.stadium.models.Teams;

@Repository
public interface TeamsRepository extends JpaRepository<Teams,Long> {
    
}
