package com.sevitours.demo.user.model;

import lombok.Data;

@Data
public class RegisterDto {

    private String username;
    private String phone;
    private String email;
    private String password;
    private String idNumber;


    public RegisterDto(String username, String phone, String idNumber, String email, String password) {
        this.username = username;
        this.phone = phone;
        this.idNumber = idNumber;
        this.email = email;
        this.password = password;
    }
}
