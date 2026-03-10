package com.sevitours.demo.tour_price.model;

import com.sevitours.demo.tour_price.enums.CustomerCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourPriceDto {

    private UUID id;
    private UUID tourOfferId;
    private CustomerCategory category;
    private Boolean isForeigner;
    private Double price;

    public TourPriceDto(TourPrice tourPrice) {
        if(tourPrice != null){
            this.id = tourPrice.getId();
            if (tourPrice.getTourOffer() != null) {
                this.tourOfferId = tourPrice.getTourOffer().getId();
            }
            this.category = tourPrice.getCategory();
            this.isForeigner = tourPrice.getIsForeigner();
            this.price = tourPrice.getPrice();
        }
    }

}
