package com.example.softwareproject.stadium.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.softwareproject.stadium.models.Role;
import com.example.softwareproject.stadium.models.User;
import com.example.softwareproject.stadium.repositories.RoleRepository;
import com.example.softwareproject.stadium.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AuthService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtService jwtService;

    public String register(@ModelAttribute User user){
       String userToJson = "";
        try{
            user.setPassword(encryptPassword(user.getPassword()));
            user.setRole(findUserRole("Name"));
            this.userRepository.save(user);
            userToJson = stringToJSON(user);
        }catch(Exception e){
            return e.toString();
        }
        return userToJson;
    }
    public Map<String,String> login(@ModelAttribute User user){
        Map<String,String> json = new HashMap<>();
        User user2 = userRepository.findByEmail(user.getEmail()).orElse(null);
        if(user2 == null)  {
            json.put("Error","Not Found");     
            return json;
        }
        if(checkPassword(user.getPassword(), user2.getPassword())) return generateJwtToken(user2);                
        json.put("Error","Not Found");
        return json; 
    }

    private Map<String,String> generateJwtToken(User user){
        String token = this.jwtService.GenerateToken(user.getId());
        Map<String,String> TokenHolder = new HashMap<>();
        TokenHolder.put("token", token);
        return TokenHolder;
    }
    private String stringToJSON(Object data){
        try{            
            ObjectMapper objectMapper = new ObjectMapper();
            String result = objectMapper.writeValueAsString(data);
            return result;
        }
        catch(Exception e){
            return e.toString();
        }
    }
    private String encryptPassword(String password){
        String passwordHash = this.bCryptPasswordEncoder.encode(password);
        return passwordHash;
    }
    private Role findUserRole(String name){
        Role userRole = this.roleRepository.findByName("User").orElse(null);
        return userRole;
    }
    private boolean checkPassword(String mainPassword,String userPassword){
        boolean matches = this.bCryptPasswordEncoder.matches(mainPassword,userPassword);
        return matches;
    }
}