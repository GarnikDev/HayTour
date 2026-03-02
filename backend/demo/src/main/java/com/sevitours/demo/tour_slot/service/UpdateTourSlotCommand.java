package com.sevitours.demo.tour_slot.service;

import com.sevitours.demo.tour_slot.model.TourSlot;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateTourSlotCommand {

    private UUID id;
    private TourSlot tourSlot;

    public UpdateTourSlotCommand(UUID id, TourSlot tourSlot) {
        this.id = id;
        this.tourSlot = tourSlot;
    }
}
