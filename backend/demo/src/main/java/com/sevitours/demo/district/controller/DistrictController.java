

package com.sevitours.demo.district.controller;

import com.sevitours.demo.district.enums.DistrictType;
import com.sevitours.demo.district.model.District;
import com.sevitours.demo.district.model.DistrictDto;
import com.sevitours.demo.district.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/districts")
public class DistrictController {

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping("/view")
    public ResponseEntity<List<DistrictDto>> getAllDistricts() {
        return districtService.getAll();
    }

    @GetMapping("/view/region/{region}")
    public ResponseEntity<List<DistrictDto>> getAllDistrictsFromRegion(@PathVariable String region){
        return districtService.getByRegion(region);
    }

    @GetMapping("/view/type/{type}")
    public ResponseEntity<List<DistrictDto>> getAllDistrictsWithType(@PathVariable DistrictType type){
        return districtService.getByType(type);
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<DistrictDto> getDistrictById(@PathVariable UUID id){
        return districtService.getById(id);
    }

    @PostMapping("/district")
    public ResponseEntity<DistrictDto> createDistrict(@RequestBody District district){
        return districtService.create(district);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteDistrict(@PathVariable UUID id){
        return districtService.delete(id);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<DistrictDto> updateDistrict(@PathVariable UUID id, @RequestBody District district){
        return districtService.update(new UpdateDistrictCommand(id, district));
    }

}
