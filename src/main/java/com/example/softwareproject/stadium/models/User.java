package com.example.softwareproject.stadium.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


@Entity //to let the spring know that this class will be a table in database
public class User implements UserDetails{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
   
    private String id;
    private String firstName;
    private String secondName;
    @Column(unique = true)
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;
    public User(){
        this.role = new Role();
        role.setId((long)1);
        role.setName("User");
    }
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String FirstName) {
        this.firstName = FirstName;
    }

    public String getSecondName() {
        return this.secondName;
    }

    public void setSecondName(String SecondName) {
        this.secondName = SecondName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String Email) {
        this.email = Email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
     return new ArrayList(Arrays.asList(new SimpleGrantedAuthority(this.role.getName())));        
    }

    @Override
    public String getUsername() {
       return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
       return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

        /*
     Role role = new Role();
role.setName("Admin");
roleRepository.save(role);

User user = new User();
user.setUsername("JohnDoe");
user.setRole(role);
userRepository.save(user);
     */     
            

}
