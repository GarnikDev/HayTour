package com.sevitours.demo.bicycle;

import com.sevitours.demo.bicycle.services.*;
import com.sevitours.demo.common.enums.BikeType;
import com.sevitours.demo.district.District;
import com.sevitours.demo.district.DistrictController;
import com.sevitours.demo.district.services.GetDistrictService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/bicycles")
public class BicycleController {

    private final CreateBicycleService createBicycleService;
    private final UpdateBicycleService updateBicycleService;
    private final GetBicycleService getBicycleService;
    private final DeleteBicycleService deleteBicycleService;
    private final GetBicycleByIdService getBicycleByIdService;

    public BicycleController(CreateBicycleService createBicycleService,
                             GetBicycleService getBicycleService,
                             UpdateBicycleService updateBicycleService,
                             DeleteBicycleService deleteBicycleService,
                             GetBicycleByIdService getBicycleByIdService) {

        this.createBicycleService = createBicycleService;
        this.getBicycleService = getBicycleService;
        this.updateBicycleService = updateBicycleService;
        this.deleteBicycleService = deleteBicycleService;
        this.getBicycleByIdService = getBicycleByIdService;
    }

    @PostMapping
    public ResponseEntity<String> createBicycle(@RequestBody Bicycle bicycle) {
        return createBicycleService.execute(bicycle);
    }

    @GetMapping
    public ResponseEntity<List<BicycleDto>> getAllBicycles() {
        return getBicycleService.execute(null);
    }

    @GetMapping
    public ResponseEntity<BicycleDto> getBicycleById(@PathVariable Integer id) {
        return getBicycleByIdService.execute(id);
    }

    @PutMapping
    public ResponseEntity<String> updateBicycle() {
        return updateBicycleService.execute(null);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBicycle() {
        return deleteBicycleService.execute(null);
    }

}
