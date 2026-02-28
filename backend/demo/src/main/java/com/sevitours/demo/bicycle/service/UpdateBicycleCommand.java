package com.sevitours.demo.bicycle.service;

import com.sevitours.demo.bicycle.model.Bicycle;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateBicycleCommand {
    private UUID id;
    private Bicycle bicycle;

    public UpdateBicycleCommand(Bicycle bicycle, UUID id) {
        this.bicycle = bicycle;
        this.id = id;
    }
}
