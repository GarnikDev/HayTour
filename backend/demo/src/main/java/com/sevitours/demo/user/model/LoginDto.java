package com.sevitours.demo.user.model;

import lombok.Data;

@Data
public class LoginDto {
    String username;
    String password;

    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
