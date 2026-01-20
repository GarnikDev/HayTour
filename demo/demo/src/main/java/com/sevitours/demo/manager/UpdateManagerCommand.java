package com.sevitours.demo.manager;

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
