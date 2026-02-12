package com.sevitours.demo.guide.model;

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

    public GuideDto(Guide guide) {
        if(guide != null){
            this.id = guide.getId();
            this.districtId = guide.getDistrict().getId();
            this.name = guide.getName();
            this.email = guide.getEmail();
            this.phone = guide.getPhone();
            this.stars = guide.getStars();
            this.startDate = guide.getStartDate();
            this.idNumber = guide.getIdNumber();
        }
    }
}
