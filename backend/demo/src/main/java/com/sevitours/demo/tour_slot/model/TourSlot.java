package com.sevitours.demo.tour_slot.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sevitours.demo.tour_offer.model.TourOffer;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"Tour_slots\"")

public class TourSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "\"Start_time\"")
    private LocalTime startTime;

    @Column(name = "\"End_time\"")
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "tourOffer_id")
    @JsonBackReference
    private TourOffer tourOffer;

}
