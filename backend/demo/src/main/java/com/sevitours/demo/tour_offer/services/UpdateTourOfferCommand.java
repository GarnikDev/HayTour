package com.sevitours.demo.tour_offer.services;

import com.sevitours.demo.tour_offer.model.TourOffer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateTourOfferCommand {

    private UUID id;
    private TourOffer tourOffer;

    public UpdateTourOfferCommand(UUID id, TourOffer tourOffer) {
        this.id = id;
        this.tourOffer = tourOffer;
    }

}
