package com.sevitours.demo.tour.services;

import com.sevitours.demo.tour.model.Tour;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateTourCommand {
    private UUID id;
    private Tour tour;

    public UpdateTourCommand(UUID id, Tour tour) {
        this.id = id;
        this.tour = tour;
    }
}
