package com.example.softwareproject.stadium.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity //to let the spring know that this class will be a table in database
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name="role_id")
    private Role role;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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
