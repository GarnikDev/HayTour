package com.sevitours.demo.manager;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ManagerDto {
    private Integer id;
    private Integer districtId;
    private String idNumber;
    private String name;
    private String email;
    private String phone;
    private LocalDate startDate;

    public ManagerDto(Manager manager) {
        if(manager != null){
            this.id = manager.getId();
            this.districtId = manager.getDistrict().getId();
            this.idNumber = manager.getIdNumber();
            this.name = manager.getName();
            this.email = manager.getEmail();
            this.phone = manager.getPhone();
            this.startDate = manager.getStartDate();
        }
    }
}
