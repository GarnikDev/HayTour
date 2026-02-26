package com.sevitours.demo.district.model;

import com.sevitours.demo.district.enums.DistrictType;
import lombok.Data;

import java.util.UUID;

@Data
public class DistrictDto {
    private UUID id;
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
