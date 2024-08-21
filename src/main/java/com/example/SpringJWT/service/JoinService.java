package com.example.SpringJWT.service;

import com.example.SpringJWT.DTO.JoinDTO;
import com.example.SpringJWT.entity.UserEntity;
import com.example.SpringJWT.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public void joinProcess(JoinDTO joinDTO){

        String nickname = joinDTO.getNickname();
        String password = joinDTO.getPassword();

        Boolean isExist = userRepository.existsByNickname(nickname);
        if (isExist){
            return;
        }

        UserEntity data = new UserEntity();

        data.setNickname(nickname);
        data.setPassword(bCryptPasswordEncoder.encode(password));
        data.setRole("ROLE_USER");
        userRepository.save(data);

    }
}
