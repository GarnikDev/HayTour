package com.sevitours.demo.tour_offer.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.sevitours.demo.user.model.AppUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name="\"TourOffer\"")
public class TourOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "\"Id\"")
    private UUID id;

    @Column(name = "\"Title\"")
    private String title;

    @Column(name = "\"Description\"")
    private String description;

    @Column(name = "\"Place\"")
    private String place;

    @Column(name = "\"Duration\"")
    private Double duration;

    @Column(name = "\"BasePrice\"")
    private Double basePrice;

    @Column(name = "\"Created_at\"")
    private OffsetDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "\"Created_by\"")
    private AppUser appUser;

    @Column(name = "\"ImageUrl\"")
    private String imageUrl;

}
