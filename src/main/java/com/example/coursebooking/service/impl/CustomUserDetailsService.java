package com.example.coursebooking.service.impl;

import com.example.coursebooking.model.AppUser;
import com.example.coursebooking.service.CustomUserDetails;
import com.example.coursebooking.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser=appUserRepository.findByEmail(username);
        if(appUser == null){
            throw new UsernameNotFoundException("User not found!");
        }
        return new CustomUserDetails(appUser);
    }


}
