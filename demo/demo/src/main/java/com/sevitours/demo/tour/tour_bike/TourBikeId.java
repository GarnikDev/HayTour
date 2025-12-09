package com.sevitours.demo.tour.tour_bike;

import java.io.Serializable;

import com.sevitours.demo.bicycle.Bicycle;
import com.sevitours.demo.tour.Tour;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class TourBikeId implements Serializable {
    private Tour tour;
    private Bicycle bicycle;
}