package com.sevitours.demo.tour_bill;

import com.sevitours.demo.tour_bill.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tour-bills")
public class TourBillController {

    private final CreateTourBillService createService;
    private final GetTourBillService getService;
    private final UpdateTourBillService updateService;
    private final DeleteTourBillService deleteService;

    public TourBillController(CreateTourBillService createService,
                              GetTourBillService getService,
                              UpdateTourBillService updateService,
                              DeleteTourBillService deleteService) {
        this.createService = createService;
        this.getService = getService;
        this.updateService = updateService;
        this.deleteService = deleteService;
    }

    @PostMapping("/tour-bill")
    public ResponseEntity<TourBillDto> create(@RequestBody TourBill bill) {
        return createService.execute(bill);
    }

    @GetMapping("/view")
    public ResponseEntity<List<TourBillDto>> getAll() {
        Void input = null;
        return getService.execute(input);
    }

    @PutMapping("/edit")
    public ResponseEntity<TourBillDto> update(@RequestBody UpdateTourBillCommand command) {
        return updateService.execute(command);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody Integer id) {
        return deleteService.execute(id);
    }
}
