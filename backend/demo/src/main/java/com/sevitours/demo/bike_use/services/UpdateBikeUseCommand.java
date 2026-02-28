package com.sevitours.demo.bike_use.services;

import com.sevitours.demo.bike_use.model.BikeUse;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateBikeUseCommand {

    private UUID id;
    private BikeUse bikeUse;

    public UpdateBikeUseCommand(UUID id, BikeUse bikeUse) {
        this.id = id;
        this.bikeUse = bikeUse;
    }
}
