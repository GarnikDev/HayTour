package com.sevitours.demo.stop.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sevitours.demo.tour_offer.model.TourOffer;
import com.sevitours.demo.user.model.AppUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"Stop\"")
public class Stop {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "\"StopID\"")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "\"TourOffer_Id\"")
    @JsonBackReference
    private TourOffer tourOffer;

    @Column(name = "\"Title\"")
    private String title;

    @Column(name = "\"Description\"")
    private String description;

    @Column(name = "\"Latitude\"")
    private Double latitude;

    @Column(name = "\"Longitude\"")
    private Double longitude;

    @Column(name = "\"Stop_order\"")
    private Integer stopOrder;

    @Column(name = "\"Created_at\"")
    private OffsetDateTime createdAt;
}
