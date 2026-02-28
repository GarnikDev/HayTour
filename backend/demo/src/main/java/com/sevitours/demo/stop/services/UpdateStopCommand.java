package com.sevitours.demo.stop.services;

import com.sevitours.demo.stop.model.Stop;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateStopCommand {

    private UUID id;
    private Stop stop;

    public UpdateStopCommand(UUID id, Stop stop) {
        this.id = id;
        this.stop = stop;
    }

}
