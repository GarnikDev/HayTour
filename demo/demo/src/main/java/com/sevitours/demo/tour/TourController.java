package com.sevitours.demo.tour;

import com.sevitours.demo.rental_bill.services.GetRentalBillService;
import com.sevitours.demo.tour.services.GetTourService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tours")
public class TourController {

    private final GetTourService getTourService;

    public TourController(GetTourService getTourService) {
        this.getTourService = getTourService;
    }

    @GetMapping
    public ResponseEntity<List<TourDto>> getAllTours(){
        return getTourService.execute(null);
    }
}
