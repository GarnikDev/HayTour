package com.sevitours.demo.manager.model;

import com.sevitours.demo.user.model.AppUser;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class ManagerDto {
    private UUID id;
    private UUID districtId;
    private LocalDate startDate;
    private AppUser user;

    public ManagerDto(Manager manager) {
        if(manager != null){
            this.id = manager.getId();
            this.districtId = manager.getDistrict().getId();
            this.startDate = manager.getStartDate();
            this.user = manager.getUser();
        }
    }
}
