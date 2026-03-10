package com.sevitours.demo.tour_price.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sevitours.demo.tour_offer.model.TourOffer;
import com.sevitours.demo.tour_price.enums.CustomerCategory;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Table
@Entity
@Data
public class TourPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "\"id\"")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "\"tour_offer\"")
    @JsonBackReference
    private TourOffer tourOffer;

    @Column(name = "\"category\"")
    @Enumerated(EnumType.STRING)
    private CustomerCategory category;

    @Column(name = "\"is_foreigner\"")
    private Boolean isForeigner;

    @Column(name = "\"price\"")
    private Double price;

}
