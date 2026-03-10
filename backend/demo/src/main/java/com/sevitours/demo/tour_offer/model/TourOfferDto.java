package com.sevitours.demo.tour_offer.model;

import com.sevitours.demo.stop.model.Stop;
import com.sevitours.demo.tour.enums.TourType;
import com.sevitours.demo.tour_price.model.TourPrice;
import com.sevitours.demo.tour_slot.model.TourSlot;
import com.sevitours.demo.user.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourOfferDto {
    private UUID id;
    private String title;
    private String description;
    private String startPoint;
    private String endPoint;
    private Integer maxGroupSize;
    private String place;
    private String duration;
    private Double basePrice;
    private OffsetDateTime createdAt;
    private UUID appUserId;
    private TourType type;
    private String imageUrl;
    private List<Stop> stops;
    private List<TourSlot> slots;
    private List<TourPrice> prices;

    public TourOfferDto(TourOffer tourOffer) {
        if(tourOffer != null){
            this.id = tourOffer.getId();
            this.title = tourOffer.getTitle();
            this.description = tourOffer.getDescription();
            this.duration = tourOffer.getDuration();
            this.place = tourOffer.getPlace();
            this.basePrice = tourOffer.getBasePrice();
            this.createdAt = tourOffer.getCreatedAt();
            this.type = tourOffer.getType();
            this.stops = tourOffer.getStops();
            this.slots = tourOffer.getSlots();
            this.prices = tourOffer.getPrices();
            if (tourOffer.getAppUser() != null) {
                this.appUserId = tourOffer.getAppUser().getId();
            }
            this.imageUrl = tourOffer.getImageUrl();
        }
    }
}
