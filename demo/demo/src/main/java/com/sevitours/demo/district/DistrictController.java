package com.sevitours.demo.district;

import com.sevitours.demo.district.services.CreateDistrictService;
import com.sevitours.demo.district.services.DeleteDistrictService;
import com.sevitours.demo.district.services.GetDistrictService;
import com.sevitours.demo.district.services.UpdateDistrictService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public ResponseEntity<List<DistrictDto>> getAllDistricts() {
        return getDistrictService.execute(null);
    }

}
