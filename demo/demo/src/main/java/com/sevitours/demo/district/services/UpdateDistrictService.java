package com.sevitours.demo.district.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.common.ItemNotFound;
import com.sevitours.demo.district.model.District;
import com.sevitours.demo.district.model.DistrictDto;
import com.sevitours.demo.district.repo.DistrictRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateDistrictService implements Command<UpdateDistrictCommand, DistrictDto> {

    private final DistrictRepository districtRepository;

    public UpdateDistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public ResponseEntity<DistrictDto> execute(UpdateDistrictCommand command){
        Optional<District> optionalDistrict = districtRepository.findById(command.getId());
        if(optionalDistrict.isPresent()){
            District district = command.getDistrict();
            district.setId(command.getId());
            districtRepository.save(district);
            return ResponseEntity.ok(new DistrictDto(district));
        }

        throw new ItemNotFound("District");
    }
}
