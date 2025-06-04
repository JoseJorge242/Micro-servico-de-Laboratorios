package com.example.Laboratorio_service.micro.model.config;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

    if (username.equals("admin")){
        throw new UsernameNotFoundException("úsuario não encontrado: " + username);
    }
    return org.springframework.security.core.userdetails.User
            .withUsername(username)
            .password("{bcrypt}$2a$10$yourEncodePassword")
            .roles("USER")
            .build();
}
}
