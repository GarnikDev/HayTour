package com.sevitours.demo.manager.services;

import com.sevitours.demo.manager.model.Manager;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateManagerCommand {
    private UUID id;
    private Manager manager;

    public UpdateManagerCommand(UUID id, Manager manager) {
        this.id = id;
        this.manager = manager;
    }
}
