package com.example.softwareproject.stadium.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.models.User;
import com.example.softwareproject.stadium.repositories.RoleRepository;
import com.example.softwareproject.stadium.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

        //finctions
}
