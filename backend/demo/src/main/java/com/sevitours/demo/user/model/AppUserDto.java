package com.sevitours.demo.user.model;

import com.sevitours.demo.user.enums.AppUserRole;
import com.sevitours.demo.user.repo.AppUserRepository;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
public class AppUserDto {

    private UUID id;
    private String username;
    private String email;
    private String phone;
    private String idNumber;
    private Boolean enabled;
    private OffsetDateTime createdAt;
    private AppUserRole role;

    public AppUserDto(AppUser appUser) {
        if(appUser != null) {
            this.id = appUser.getId();
            this.username = appUser.getUsername();
            this.email = appUser.getEmail();
            this.phone = appUser.getPhone();
            this.idNumber = appUser.getIdNumber();
            this.enabled = appUser.getEnabled();
            this.createdAt = appUser.getCreatedAt();
            this.role = appUser.getRole();
        }
    }
}
