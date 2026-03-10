package com.sevitours.demo.tour_offer.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sevitours.demo.stop.model.Stop;
import com.sevitours.demo.tour.enums.TourType;
import com.sevitours.demo.tour_price.model.TourPrice;
import com.sevitours.demo.tour_slot.model.TourSlot;
import com.sevitours.demo.user.model.AppUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
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

    @Column(name = "\"Description\"", columnDefinition = "TEXT")
    private String description;

    @Column(name = "\"startPoint\"")
    private String startPoint;

    @Column(name = "\"endPoint\"")
    private String endPoint;

    @Column(name = "\"maxGroupSize\"")
    private Integer maxGroupSize;

    @Column(name = "\"Place\"")
    private String place;

    @Column(name = "\"Duration\"")
    private String duration;

    @Column(name = "\"BasePrice\"")
    private Double basePrice;

    @Column(name = "\"Created_at\"")
    private OffsetDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "\"Created_by\"")
    private AppUser appUser;

    @Column(name = "\"Type\"")
    @Enumerated(EnumType.STRING)
    private TourType type;

    @Column(name = "\"ImageUrl\"")
    private String imageUrl;

    @OneToMany(mappedBy = "tourOffer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    @OrderBy("stopOrder ASC")
    private List<Stop> stops = new ArrayList<>();

    @OneToMany(mappedBy = "tourOffer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TourSlot> slots = new ArrayList<>();

    @OneToMany(mappedBy = "tourOffer", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<TourPrice> prices = new ArrayList<>();

}
