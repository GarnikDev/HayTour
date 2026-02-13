package com.sevitours.demo.client.services;

import com.sevitours.demo.client.model.Customer;
import lombok.Getter;

@Getter
public class UpdateCustomerCommand {
    private Customer customer;
    private Integer id;

    public UpdateCustomerCommand(Integer id, Customer customer) {
        this.customer = customer;
        this.id = id;
    }
}
