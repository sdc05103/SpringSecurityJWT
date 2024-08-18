package com.example.SpringJWT.controller;


import com.example.SpringJWT.DTO.JoinDTO;
import com.example.SpringJWT.service.JoinService;
import org.hibernate.mapping.Join;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService){
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public String joinProcess(JoinDTO joinDTO){
        joinService.joinProcess(joinDTO);
        return "ok";
    }


}
