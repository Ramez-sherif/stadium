package com.example.softwareproject.stadium.services;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.softwareproject.stadium.models.Role;
import com.example.softwareproject.stadium.models.User;
import com.example.softwareproject.stadium.repositories.RoleRepository;
import com.example.softwareproject.stadium.repositories.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AuthService implements AuthenticationProvider{
    @Autowired
    private UserSecurityService userSecurityService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private JwtService jwtService;

    public User register(@ModelAttribute User user){
       //String userToJson = "";
        try{
            user.setPassword(encryptPassword(user.getPassword()));
            return this.userRepository.save(user);
            //userToJson = stringToJSON(user);
           
        }catch(Exception e){
            return null;
        }
        //return  generateJwtToken(user);
    }
    public Map<String,String> login(@ModelAttribute User user){
        Map<String,String> json = new HashMap<>();
        User user2 = userRepository.findByEmail(user.getEmail()).orElse(null);
        if(user2 == null)  {
   
            return null;
        }
        if(checkPassword(user.getPassword(), user2.getPassword())) return generateJwtToken(user2);                
    
        return null; 
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
    public Role getRoleByName(String Name){
        return this.roleRepository.findByName(Name).orElse(null);

    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{
        //System.out.println(authentication);
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = this.userSecurityService.loadUserByUsername(email);
        if(user == null){
            //throw new UsernameNotFoundException("Invalid Username");   
            return null; 
        }
        if(!checkPassword(password, user.getPassword())){
            //throw new UsernameNotFoundException("Invalid password"); 
            return null;   
        }
        UserDetails userDetails = user;
        return new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
    }
    @Override
    public boolean supports(Class<?> authentication) {
       return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}