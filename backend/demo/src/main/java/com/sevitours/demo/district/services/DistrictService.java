package com.sevitours.demo.district.services;

import com.sevitours.demo.common.ItemNotFound;
import com.sevitours.demo.district.enums.DistrictType;
import com.sevitours.demo.district.model.District;
import com.sevitours.demo.district.model.DistrictDto;
import com.sevitours.demo.district.model.DistrictMapper;
import com.sevitours.demo.district.repo.DistrictRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DistrictService {

    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;

    public DistrictService(DistrictRepository districtRepository, DistrictMapper districtMapper) {
        this.districtRepository = districtRepository;
        this.districtMapper = districtMapper;
    }

    public ResponseEntity<DistrictDto> create(District district) {
        District savedDistrict = districtRepository.save(district);
        return ResponseEntity.status(HttpStatus.CREATED).body(new DistrictDto(savedDistrict));
    }

    public ResponseEntity<Void> delete(UUID id) {

        Optional<District> districtOptional = districtRepository.findById(id);
        if(districtOptional.isPresent()){
            districtRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        throw new ItemNotFound("District");
    }

    public ResponseEntity<List<DistrictDto>> getAll() {
        List<DistrictDto> districtDtos = districtRepository.findAll()
                .stream()
                .map(districtMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(districtDtos);
    }

    public ResponseEntity<List<DistrictDto>> getByRegion(String region){// get districts in inputed region
        List<DistrictDto> districtDtos = districtRepository.findAllByRegion(region)
                .stream()
                .map(districtMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(districtDtos);
    }

    public ResponseEntity<List<DistrictDto>> getByType(DistrictType type){// get districts with inputed type of region
        List<DistrictDto> districtDtos = districtRepository.findAllByType(type)
                .stream()
                .map(districtMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(districtDtos);
    }

    public ResponseEntity<DistrictDto> getById(UUID id){// get district by id
        Optional<District> districtWithId = districtRepository.findById(id);
        if(districtWithId.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(districtMapper.toDto(districtWithId.get()));
        }else{
            throw new ItemNotFound("District");
        }
    }

    public ResponseEntity<DistrictDto> update(UpdateDistrictCommand command){
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
