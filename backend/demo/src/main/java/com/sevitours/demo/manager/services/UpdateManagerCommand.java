package com.sevitours.demo.manager.services;

import com.sevitours.demo.manager.model.Manager;
import lombok.Getter;

@Getter
public class UpdateManagerCommand {
    private Integer id;
    private Manager manager;

    public UpdateManagerCommand(Integer id, Manager manager) {
        this.id = id;
        this.manager = manager;
    }
}
