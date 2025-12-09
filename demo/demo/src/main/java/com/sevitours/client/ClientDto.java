package com.sevitours.client;

import com.sevitours.common.enums.AgeCategory;
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
