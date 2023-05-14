package com.example.softwareproject.stadium.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private AuthenticationFilter authenticationFilter;
    @Bean
    public SecurityFilterChain filterchain(HttpSecurity httpSecurtiy) throws Exception{
        httpSecurtiy.csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authorizeRequests()// any role can access these end points
        .antMatchers("/auth/register","/auth/login","/match/add","/category/add","/stadium/add").permitAll()
        .antMatchers("/we").hasAuthority("USER")
        .anyRequest().authenticated()
        .and()
        .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurtiy.build();
    }  
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
 