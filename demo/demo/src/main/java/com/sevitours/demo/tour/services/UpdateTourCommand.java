package com.sevitours.demo.tour.services;

import com.sevitours.demo.tour.model.Tour;
import lombok.Getter;

@Getter
public class UpdateTourCommand {
    private Integer id;
    private Tour tour;

    public UpdateTourCommand(Integer id, Tour tour) {
        this.id = id;
        this.tour = tour;
    }
}
