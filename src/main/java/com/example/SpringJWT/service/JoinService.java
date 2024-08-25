package com.example.SpringJWT.service;

import com.example.SpringJWT.DTO.JoinDTO;
import com.example.SpringJWT.entity.Traveler;
import com.example.SpringJWT.repository.TravelerRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final TravelerRepository travelerRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public JoinService(TravelerRepository travelerRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.travelerRepository = travelerRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JoinDTO joinDTO){

        String nickname = joinDTO.getNickname();
        String password = joinDTO.getPassword();

        Boolean isExist = travelerRepository.existsByNickname(nickname);
        if (isExist){
            return;
        }

        Traveler data = new Traveler();

        data.setNickname(nickname);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole("ROLE_USER");
        travelerRepository.save(data);

    }
}
