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

    public BicycleController(CreateBicycleService createBicycleService,
                             GetBicycleService getBicycleService,
                             UpdateBicycleService updateBicycleService,
                             DeleteBicycleService deleteBicycleService) {

        this.createBicycleService = createBicycleService;
        this.getBicycleService = getBicycleService;
        this.updateBicycleService = updateBicycleService;
        this.deleteBicycleService = deleteBicycleService;
    }

    @PostMapping("/bicycle")
    public ResponseEntity<BicycleDto> createBicycle(@RequestBody Bicycle bicycle) {
        return createBicycleService.execute(bicycle);
    }

    @GetMapping("/view")
    public ResponseEntity<List<BicycleDto>> getAllBicycles() {
        Void input = null;
        return getBicycleService.execute(input);
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<BicycleDto> getBicycleById(@PathVariable Long id) {
        return getBicycleService.execute(id);
    }

    @GetMapping("/view/type/{type}")
    public ResponseEntity<List<BicycleDto>> getAllBicyclesByType(@PathVariable BikeType type) {
        return getBicycleService.execute(type);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<BicycleDto> updateBicycle(@PathVariable Long id, @RequestBody Bicycle bicycle) {
        return updateBicycleService.execute(new UpdateBicycleCommand(bicycle, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBicycle(@PathVariable Long id) {
        return deleteBicycleService.execute(id);
    }

}
