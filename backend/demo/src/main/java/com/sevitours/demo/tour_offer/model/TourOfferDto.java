package com.sevitours.demo.tour_offer.model;

import com.sevitours.demo.user.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourOfferDto {
    private UUID id;
    private String title;
    private String description;
    private Double duration;
    private String place;
    private Double basePrice;
    private OffsetDateTime createdAt;
    private UUID appUserId;
    private String imageUrl;

    public TourOfferDto(TourOffer tourOffer) {
        if(tourOffer != null){
            this.id = tourOffer.getId();
            this.title = tourOffer.getTitle();
            this.description = tourOffer.getDescription();
            this.duration = tourOffer.getDuration();
            this.place = tourOffer.getPlace();
            this.basePrice = tourOffer.getBasePrice();
            this.createdAt = tourOffer.getCreatedAt();
            if (tourOffer.getAppUser() != null) {
                this.appUserId = tourOffer.getAppUser().getId();
            }
            this.imageUrl = tourOffer.getImageUrl();
        }
    }
}
