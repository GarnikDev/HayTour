package com.sevitours.demo.bicycle;

import lombok.Getter;

@Getter
public class UpdateBicycleCommand {
    private Integer id;
    private Bicycle bicycle;

    public UpdateBicycleCommand(Bicycle bicycle, Integer id) {
        this.bicycle = bicycle;
        this.id = id;
    }
}
