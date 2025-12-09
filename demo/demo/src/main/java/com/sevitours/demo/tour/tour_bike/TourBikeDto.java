package com.sevitours.demo.tour.tour_bike;  // Or tour_bike/dto

import com.sevitours.demo.bicycle.Bicycle;
import com.sevitours.demo.tour.Tour;
import lombok.Data;

@Data
public class TourBikeDto {
    private Tour tour;
    private Bicycle bicycle;
}