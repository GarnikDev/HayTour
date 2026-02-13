package com.sevitours.demo.client.model;

import com.sevitours.demo.user.model.AppUser;
import lombok.Data;

@Data
public class CustomerDto {
    private Integer id;
    private String country;
    private String language;
    private AppUser user;

    public CustomerDto(Customer customer) {
        if(customer != null){
            this.id = customer.getId();
            this.country = customer.getCountry();
            this.language = customer.getLanguage();
            this.user = customer.getUser();
        }
    }
}
