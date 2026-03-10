package com.sevitours.demo.tour_price.service;

import com.sevitours.demo.tour_price.model.TourPrice;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateTourPriceCommand {

    private UUID id;
    private TourPrice tourPrice;

    public UpdateTourPriceCommand(UUID id, TourPrice tourPrice) {
        this.id = id;
        this.tourPrice = tourPrice;
    }
}
