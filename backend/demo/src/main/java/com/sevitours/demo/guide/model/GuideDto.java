package com.sevitours.demo.guide.model;

import com.sevitours.demo.user.model.AppUser;
import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class GuideDto {
    private UUID id;
    private UUID districtId;
    private Integer stars;
    private LocalDate startDate;
    private AppUser user;

    public GuideDto(Guide guide) {
        if(guide != null){
            this.id = guide.getId();
            this.districtId = guide.getDistrict().getId();
            this.stars = guide.getStars();
            this.startDate = guide.getStartDate();
            this.user = guide.getUser();
        }
    }
}
