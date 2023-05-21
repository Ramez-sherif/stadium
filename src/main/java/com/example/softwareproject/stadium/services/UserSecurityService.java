package com.example.softwareproject.stadium.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.softwareproject.stadium.models.User;
import com.example.softwareproject.stadium.repositories.UserRepository;
@Service
public class UserSecurityService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        
        User user = this.userRepository.findByEmail(username).orElse(null);
        if(user == null){
            System.out.println(username);
            System.out.println("fuckkkkkkk");
            return null;
        }
        return user;
        
    }
    
}
