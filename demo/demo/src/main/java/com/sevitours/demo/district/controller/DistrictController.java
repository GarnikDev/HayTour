

package com.sevitours.demo.district.controller;

import com.sevitours.demo.district.enums.DistrictType;
import com.sevitours.demo.district.model.District;
import com.sevitours.demo.district.model.DistrictDto;
import com.sevitours.demo.district.services.UpdateDistrictCommand;
import com.sevitours.demo.district.services.CreateDistrictService;
import com.sevitours.demo.district.services.DeleteDistrictService;
import com.sevitours.demo.district.services.GetDistrictService;
import com.sevitours.demo.district.services.UpdateDistrictService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/districts")
public class DistrictController {

    private final CreateDistrictService createDistrictService;
    private final GetDistrictService getDistrictService;
    private final UpdateDistrictService updateDistrictService;
    private final DeleteDistrictService deleteDistrictService;

    public DistrictController(CreateDistrictService createDistrictService,
                              GetDistrictService getDistrictService,
                              UpdateDistrictService updateDistrictService,
                              DeleteDistrictService deleteDistrictService) {

        this.createDistrictService = createDistrictService;
        this.getDistrictService = getDistrictService;
        this.updateDistrictService = updateDistrictService;
        this.deleteDistrictService = deleteDistrictService;
    }

    @GetMapping("/view")
    public ResponseEntity<List<DistrictDto>> getAllDistricts() {
        Void input = null;
        return getDistrictService.execute(input);
    }

    @GetMapping("/view/region/{region}")
    public ResponseEntity<List<DistrictDto>> getAllDistrictsFromRegion(@PathVariable String region){
        return getDistrictService.execute(region);
    }

    @GetMapping("/view/type/{type}")
    public ResponseEntity<List<DistrictDto>> getAllDistrictsWithType(@PathVariable DistrictType type){
        return getDistrictService.execute(type);
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<DistrictDto> getDistrictById(@PathVariable Integer id){
        return getDistrictService.execute(id);
    }

    @PostMapping("/district")
    public ResponseEntity<DistrictDto> createDistrict(@RequestBody District district){
        return createDistrictService.execute(district);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteDistrict(@PathVariable Integer id){
        return deleteDistrictService.execute(id);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<DistrictDto> updateDistrict(@PathVariable Integer id, @RequestBody District district){
        return updateDistrictService.execute(new UpdateDistrictCommand(id, district));
    }

}
