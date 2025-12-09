package com.sevitours.tour.tour_bike;

import com.sevitours.bicycle.Bicycle;
import com.sevitours.tour.Tour;
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