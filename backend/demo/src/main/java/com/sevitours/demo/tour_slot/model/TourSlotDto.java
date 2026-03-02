package com.sevitours.demo.tour_slot.model;


import lombok.Data;

import java.time.LocalTime;
import java.util.UUID;

@Data
public class TourSlotDto {

    private LocalTime startTime;
    private LocalTime endTime;
    private UUID tourId;

    public TourSlotDto(TourSlot tourSlot) {
        this.startTime = tourSlot.getStartTime();
        this.endTime = tourSlot.getEndTime();
        this.tourId = tourSlot.getTour().getId();
    }
}
