package com.sevitours.demo.district.services;

import com.sevitours.demo.district.model.District;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateDistrictCommand {
    private UUID id;
    private District district;

    public UpdateDistrictCommand(UUID id, District district) {
        this.id = id;
        this.district = district;
    }
}
