package com.sevitours.demo.tour.tour_bike;

import com.sevitours.demo.bicycle.Bicycle;
import com.sevitours.demo.tour.Tour;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"Tour_Bike\"")
@Data
@IdClass(TourBikeId.class)
public class TourBike {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Tour_id\"", nullable = false)
    private Tour tour;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "\"Bicycle_id\"", nullable = false)
    private Bicycle bicycle;
}