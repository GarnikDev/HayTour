package com.sevitours.demo.rental;

import com.sevitours.demo.manager.ManagerDto;
import com.sevitours.demo.manager.services.GetManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final GetManagerService getManagerService;

    public RentalController(GetManagerService getManagerService) {
        this.getManagerService = getManagerService;
    }

    @GetMapping
    public ResponseEntity<List<ManagerDto>> getAllRentals(){
        return getManagerService.execute(null);
    }
}
