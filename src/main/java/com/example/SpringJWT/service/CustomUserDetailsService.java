package com.example.SpringJWT.service;

import com.example.SpringJWT.DTO.CustomUserDetails;
import com.example.SpringJWT.entity.Traveler;
import com.example.SpringJWT.repository.TravelerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final TravelerRepository travelerRepository;

    public CustomUserDetailsService(TravelerRepository travelerRepository){
        this.travelerRepository = travelerRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {


        Traveler userData = travelerRepository.findByNickname(nickname);
        System.out.println(nickname);

        if(userData != null){
            return new CustomUserDetails(userData);
        }

        return null;
    }
}
