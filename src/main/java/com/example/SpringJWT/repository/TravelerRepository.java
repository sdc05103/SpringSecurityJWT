package com.example.SpringJWT.repository;

import com.example.SpringJWT.entity.Traveler;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelerRepository extends JpaRepository <Traveler, Long> {
    Boolean existsByNickname(String nickname);

    Traveler findByNickname(String nickname);

}
