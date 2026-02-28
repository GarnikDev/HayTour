package com.sevitours.demo.client.services;

import com.sevitours.demo.client.model.Customer;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateCustomerCommand {
    private Customer customer;
    private UUID id;

    public UpdateCustomerCommand(UUID id, Customer customer) {
        this.customer = customer;
        this.id = id;
    }
}
