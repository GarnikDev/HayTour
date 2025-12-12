package com.sevitours.demo.rental_bill;

import com.sevitours.demo.rental_bill.services.GetRentalBillService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rentalbills")
public class RentalBillController {

    private final GetRentalBillService getRentalBillService;

    public RentalBillController(GetRentalBillService getRentalBillService) {
        this.getRentalBillService = getRentalBillService;
    }

    @GetMapping
    public ResponseEntity<List<RentalBillDto>> getAllRentalBills(){
        return getRentalBillService.execute(null);
    }
}
