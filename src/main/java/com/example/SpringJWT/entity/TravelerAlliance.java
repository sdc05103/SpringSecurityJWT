package com.example.SpringJWT.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class TravelerAlliance {

    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "traveler_id")
    private Traveler traveler;

    @ManyToOne
    @JoinColumn(name = "alliance_id")
    private Alliance alliance;

}