package com.sevitours.bicycle;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BicycleController {

    @PostMapping
    public Bicycle create(@RequestBody Bicycle bicycle) {
        return bicycle;
    }

    @GetMapping
    public List<Bicycle> findAll() {
        return null;
    }

    @PutMapping
    public Bicycle update(@RequestBody Bicycle bicycle) {
        return bicycle;
    }

    @DeleteMapping
    public void delete(@RequestBody Bicycle bicycle) {
        return;
    }
}
