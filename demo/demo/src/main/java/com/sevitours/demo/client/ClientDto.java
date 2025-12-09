package com.sevitours.demo.client;

import lombok.Data;

@Data
public class ClientDto {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String country;
    private String language;
}
