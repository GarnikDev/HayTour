package com.sevitours.demo.client.model;

import com.sevitours.demo.user.model.AppUser;
import lombok.Data;

import java.util.UUID;

@Data
public class CustomerDto {
    private UUID id;
    private String country;
    private Integer languageId;
    private AppUser user;

    public CustomerDto(Customer customer) {
        if(customer != null){
            this.id = customer.getId();
            this.country = customer.getCountry();
            this.languageId = customer.getLanguage().getId();
            this.user = customer.getUser();
        }
    }
}
