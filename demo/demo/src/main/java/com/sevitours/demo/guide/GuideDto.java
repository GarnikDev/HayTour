package com.sevitours.demo.guide;

import lombok.Data;
import java.time.LocalDate;

@Data
public class GuideDto {
    private Integer id;
    private Integer districtId;
    private String name;
    private String email;
    private String phone;
    private Integer stars;
    private LocalDate startDate;
    private String idNumber;
}
