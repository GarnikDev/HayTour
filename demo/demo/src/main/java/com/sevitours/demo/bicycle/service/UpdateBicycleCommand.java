package com.sevitours.demo.bicycle.service;

import com.sevitours.demo.bicycle.model.Bicycle;
import lombok.Getter;

@Getter
public class UpdateBicycleCommand {
    private Long id;
    private Bicycle bicycle;

    public UpdateBicycleCommand(Bicycle bicycle, Long id) {
        this.bicycle = bicycle;
        this.id = id;
    }
}
