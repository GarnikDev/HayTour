package com.sevitours.demo.district.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.district.District;
import com.sevitours.demo.district.DistrictDto;
import com.sevitours.demo.district.DistrictRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteDistrictService implements Command<Integer, Void> {

    private final DistrictRepository districtRepository;

    public DeleteDistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }


    @Override
    public ResponseEntity<Void> execute(Integer id) {

        Optional<District> districtOptional = districtRepository.findById(id);
        if(districtOptional.isPresent()){
            districtRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return null;
    }
}
