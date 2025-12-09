package com.sevitours.tour.tour_bike;

import java.io.Serializable;

import com.sevitours.bicycle.Bicycle;
import com.sevitours.tour.Tour;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class TourBikeId implements Serializable {
    private Tour tour;
    private Bicycle bicycle;
}