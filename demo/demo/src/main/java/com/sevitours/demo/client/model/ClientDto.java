package com.sevitours.demo.client.model;

import com.sevitours.demo.user.model.AppUser;
import lombok.Data;

@Data
public class ClientDto {
    private Integer id;
    private String country;
    private String language;
    private AppUser user;

    public ClientDto(Client client) {
        if(client != null){
            this.id = client.getId();
            this.country = client.getCountry();
            this.language = client.getLanguage();
            this.user = client.getUser();
        }
    }
}
