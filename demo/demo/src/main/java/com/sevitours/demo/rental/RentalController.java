package com.sevitours.demo.rental;

import com.sevitours.demo.rental.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final CreateRentalService createRentalService;
    private final GetRentalService getRentalService;
    private final UpdateRentalService updateRentalService;
    private final DeleteRentalService deleteRentalService;

    public RentalController(CreateRentalService createRentalService,
                            GetRentalService getRentalService,
                            UpdateRentalService updateRentalService,
                            DeleteRentalService deleteRentalService) {
        this.createRentalService = createRentalService;
        this.getRentalService = getRentalService;
        this.updateRentalService = updateRentalService;
        this.deleteRentalService = deleteRentalService;
    }

    @PostMapping("/rental")
    public ResponseEntity<RentalDto> create(@RequestBody Rental rental) {
        return createRentalService.execute(rental);
    }

    @GetMapping("/view")
    public ResponseEntity<List<RentalDto>> getAll() {
        Void input = null;
        return getRentalService.execute(input);
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<RentalDto> getById(@PathVariable Integer id) {
        return getRentalService.execute(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<RentalDto> update(@PathVariable Integer id, @RequestBody Rental rental) {
        return updateRentalService.execute(new UpdateRentalCommand(id, rental));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return deleteRentalService.execute(id);
    }
}