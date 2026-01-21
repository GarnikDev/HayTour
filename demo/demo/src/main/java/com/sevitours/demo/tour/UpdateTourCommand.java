package com.sevitours.demo.tour;

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
