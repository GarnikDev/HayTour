package com.sevitours.demo.bicycle;

import com.sevitours.demo.bicycle.services.CreateBicycleService;
import com.sevitours.demo.bicycle.services.DeleteBicycle;
import com.sevitours.demo.bicycle.services.GetBicycleService;
import com.sevitours.demo.bicycle.services.UpdateBicycleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class BicycleController {

    private final CreateBicycleService createBicycleService;
    private final UpdateBicycleService updateBicycleService;
    private final GetBicycleService getBicycleService;
    private final DeleteBicycle deleteBicycle;

    public BicycleController(CreateBicycleService createBicycleService, GetBicycleService getBicycleService, UpdateBicycleService updateBicycleService, DeleteBicycle deleteBicycle) {
        this.createBicycleService = createBicycleService;
        this.getBicycleService = getBicycleService;
        this.updateBicycleService = updateBicycleService;
        this.deleteBicycle = deleteBicycle;
    }


    @PostMapping
    public ResponseEntity<String> createBicycle() {
        return createBicycleService.execute(null);
    }

    @GetMapping
    public ResponseEntity<List<Bicycle>> readBicycle() {
        return getBicycleService.execute(null);
    }

    @PutMapping
    public ResponseEntity<String> updateBicycle() {
        return updateBicycleService.execute(null);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBicycle() {
        return deleteBicycle.execute(null);
    }

}
