package com.sevitours.manager;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ManagerDto {
    private Integer id;
    private Integer districtId;
    private String idNumber;
    private String name;
    private String phone;
    private LocalDate startDate;
}
