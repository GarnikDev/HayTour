package com.sevitours.demo.client.model;

import lombok.Data;

@Data
public class ClientDto {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String country;
    private String language;

    public ClientDto(Client client) {
        if(client != null){
            this.id = client.getId();
            this.name = client.getName();
            this.email = client.getEmail();
            this.phone = client.getPhone();
            this.country = client.getCountry();
            this.language = client.getLanguage();
        }
    }
}
