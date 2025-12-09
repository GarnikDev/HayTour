package com.sevitours.guide;

import lombok.Data;
import java.time.LocalDate;

@Data
public class GuideDto {
    private Integer id;
    private Integer districtId;
    private String idNumber;
    private String name;
    private String email;
    private String phone;
    private Integer stars;
    private LocalDate startDate;
}
