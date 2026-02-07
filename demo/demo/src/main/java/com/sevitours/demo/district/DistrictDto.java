package com.sevitours.demo.district;

import com.sevitours.demo.common.enums.DistrictType;
import lombok.Data;

@Data
public class DistrictDto {
    private Integer id;
    private String region;
    private DistrictType type;
    private String address;
    private String email;

    public DistrictDto(District district) {
        if(district != null){
            this.id = district.getId();
            this.region = district.getRegion();
            this.type = district.getType();
            this.address = district.getAddress();
        }
    }
}
