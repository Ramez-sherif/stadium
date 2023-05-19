package com.example.softwareproject.stadium.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.models.Role;
import com.example.softwareproject.stadium.models.User;
import com.example.softwareproject.stadium.repositories.RoleRepository;
import com.example.softwareproject.stadium.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;

        //functions
        public User login(User user){
            //if user with e,ail and passwrod exists 
            //login or return that user
            //else returm mull
            ////if(userRepository.)
            return null;
        }
        public User register(){
            return null;
        }
        public void function(User user){
            //get from database where role name

        }
     
}
