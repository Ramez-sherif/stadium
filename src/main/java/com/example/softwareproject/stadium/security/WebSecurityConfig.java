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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.softwareproject.stadium.services.UserSecurityService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private UserSecurityService userSecurityService;
    
    @Autowired
    private AuthenticationFilter authenticationFilter;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterchain(HttpSecurity httpSecurtiy) throws Exception{
        httpSecurtiy.userDetailsService(userSecurityService)
        .authorizeRequests()
        //any role can access these end points
        //.antMatchers("/auth/login","/auth/login/save","/auth/logout").permitAll()
        //.antMatchers("/*","/*/*","/*/*/*").permitAll()
        //only the role user can access these endpoints
       // .antMatchers("/User").hasAuthority("USER")
        //only the Admin user can access these endpoints
      //  .antMatchers("/Admin" ).hasAuthority("ADMIN")
         //only the Store Manager user can access these endpoints
      // .antMatchers("/StoreManager").hasAuthority("STOREMANAGER")
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .loginPage("/auth/login")
        .loginProcessingUrl("/auth/login/save")
        .defaultSuccessUrl("/auth/register")
        .permitAll()
        .and()
        .logout()
        .permitAll()
        .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"))
        .logoutSuccessUrl("/auth/login");
        //.and()   
        //.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurtiy.build();
    }  
}
 