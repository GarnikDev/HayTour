package com.sevitours.demo.rental_bill;

import com.sevitours.demo.rental_bill.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental-bills")
public class RentalBillController {

    private final CreateRentalBillService createService;
    private final GetRentalBillService getService;
    private final UpdateRentalBillService updateService;
    private final DeleteRentalBillService deleteService;

    public RentalBillController(CreateRentalBillService createService,
                                GetRentalBillService getService,
                                UpdateRentalBillService updateService,
                                DeleteRentalBillService deleteService) {
        this.createService = createService;
        this.getService = getService;
        this.updateService = updateService;
        this.deleteService = deleteService;
    }

    @PostMapping("/rental-bill")
    public ResponseEntity<RentalBillDto> create(@RequestBody RentalBill bill) {
        return createService.execute(bill);
    }

    @GetMapping("/view")
    public ResponseEntity<List<RentalBillDto>> getAll() {
        Void input = null;
        return getService.execute(input);
    }

    @PutMapping("/edit")
    public ResponseEntity<RentalBillDto> update(@RequestBody UpdateRentalBillCommand command) {
        return updateService.execute(command);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestBody Integer id) {
        return deleteService.execute(id);
    }
}
