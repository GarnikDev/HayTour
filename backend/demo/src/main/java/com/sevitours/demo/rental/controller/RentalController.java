package com.sevitours.demo.rental.controller;

import com.sevitours.demo.rental.model.Rental;
import com.sevitours.demo.rental.model.RentalDto;
import com.sevitours.demo.rental.services.UpdateRentalCommand;
import com.sevitours.demo.rental.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rentals")
public class RentalController {

    private final RentalService rentalService;

    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping("/rental")
    public ResponseEntity<RentalDto> create(@RequestBody Rental rental) {
        return rentalService.create(rental);
    }

    @GetMapping("/view")
    public ResponseEntity<List<RentalDto>> getAll() {
        return rentalService.getAll();
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<RentalDto> getById(@PathVariable UUID id) {
        return rentalService.getById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<RentalDto> update(@PathVariable UUID id, @RequestBody Rental rental) {
        return rentalService.update(new UpdateRentalCommand(id, rental));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return rentalService.delete(id);
    }
}