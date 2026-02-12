package com.sevitours.demo.district.services;

import com.sevitours.demo.Query;
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

@Service
public class GetDistrictService implements Query<Void, List<DistrictDto>> {

    private final DistrictRepository districtRepository;
    private final DistrictMapper districtMapper;

    public GetDistrictService(DistrictRepository districtRepository,
                              DistrictMapper districtMapper) {
        this.districtRepository = districtRepository;
        this.districtMapper = districtMapper;
    }

    @Override
    public ResponseEntity<List<DistrictDto>> execute(Void input){
        List<DistrictDto> districtDtos = districtRepository.findAll()
                .stream()
                .map(districtMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(districtDtos);
    }

    public ResponseEntity<List<DistrictDto>> execute(String region){// get districts in inputed region
        List<DistrictDto> districtDtos = districtRepository.findAllByRegion(region)
                .stream()
                .map(districtMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(districtDtos);
    }

    public ResponseEntity<List<DistrictDto>> execute(DistrictType type){// get districts with inputed type of region
        List<DistrictDto> districtDtos = districtRepository.findAllByType(type)
                .stream()
                .map(districtMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(districtDtos);
    }

    public ResponseEntity<DistrictDto> execute(Integer id){// get district by id
        Optional<District> districtWithId = districtRepository.findById(id);
        if(districtWithId.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(districtMapper.toDto(districtWithId.get()));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
