package com.sevitours.demo.tour_slot.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourSlotDto {

    private LocalTime startTime;
    private LocalTime endTime;
    private UUID tourOfferId;

    public TourSlotDto(TourSlot tourSlot) {
        this.startTime = tourSlot.getStartTime();
        this.endTime = tourSlot.getEndTime();
        if (tourSlot.getTourOffer() != null) {
            this.tourOfferId = tourSlot.getTourOffer().getId();
        }
    }
}
