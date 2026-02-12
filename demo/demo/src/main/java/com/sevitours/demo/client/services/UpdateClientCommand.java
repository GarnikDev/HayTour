package com.sevitours.demo.client.services;

import com.sevitours.demo.client.model.Client;
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
