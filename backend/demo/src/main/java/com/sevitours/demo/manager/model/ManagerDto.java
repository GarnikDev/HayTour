package com.sevitours.demo.manager.model;

import com.sevitours.demo.user.model.AppUser;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ManagerDto {
    private Integer id;
    private Integer districtId;
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
