package com.sevitours.demo.user.model;

import com.sevitours.demo.user.enums.AppUserRole;
import com.sevitours.demo.user.repo.AppUserRepository;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class AppUserDto {

    private Long id;
    private String name;
    private String email;
    private String phone;
    private String idNumber;
    private String password;
    private Boolean enabled;
    private OffsetDateTime createdAt;
    private AppUserRole role;

    public AppUserDto(AppUser appUser) {
        if(appUser != null) {
            this.id = appUser.getId();
            this.name = appUser.getName();
            this.email = appUser.getEmail();
            this.phone = appUser.getPhone();
            this.idNumber = appUser.getIdNumber();
            this.password = appUser.getPassword();
            this.enabled = appUser.getEnabled();
            this.createdAt = appUser.getCreatedAt();
            this.role = appUser.getRole();
        }
    }
}
