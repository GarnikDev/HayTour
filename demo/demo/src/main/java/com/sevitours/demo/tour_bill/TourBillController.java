package com.sevitours.demo.tour_bill;

import com.sevitours.demo.tour_bill.services.GetTourBillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tourbills")
public class TourBillController {

    private final GetTourBillService getTourBillService;

    public TourBillController(GetTourBillService getTourBillService) {
        this.getTourBillService = getTourBillService;
    }

    @GetMapping
    public ResponseEntity<List<TourBillDto>> getAllTour(){
        return getTourBillService.execute(null);
    }
}
