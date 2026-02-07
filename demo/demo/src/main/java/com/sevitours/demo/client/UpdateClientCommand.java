package com.sevitours.demo.client;

import lombok.Getter;

@Getter
public class UpdateClientCommand {
    private Client client;
    private Integer id;

    public UpdateClientCommand(Integer id, Client client) {
        this.client = client;
        this.id = id;
    }
}
