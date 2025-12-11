package com.sevitours.demo.district.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.district.DistrictDto;
import com.sevitours.demo.district.DistrictMapper;
import com.sevitours.demo.district.DistrictRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
