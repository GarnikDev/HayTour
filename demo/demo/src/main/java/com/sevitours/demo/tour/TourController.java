package com.sevitours.demo.tour;

import com.sevitours.demo.tour.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tours")
public class TourController {

    private final CreateTourService createService;
    private final GetTourService getService;
    private final UpdateTourService updateService;
    private final DeleteTourService deleteService;

    public TourController(CreateTourService createService,
                          GetTourService getService,
                          UpdateTourService updateService,
                          DeleteTourService deleteService) {
        this.createService = createService;
        this.getService = getService;
        this.updateService = updateService;
        this.deleteService = deleteService;
    }

    @PostMapping("/tour")
    public ResponseEntity<TourDto> create(@RequestBody Tour tour) {
        return createService.execute(tour);
    }

    @GetMapping("/view")
    public ResponseEntity<List<TourDto>> getAll() {
        Void input = null;
        return getService.execute(input);
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<TourDto> getById(@PathVariable Integer id) {
        return getService.execute(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TourDto> update(@PathVariable Integer id, @RequestBody Tour tour) {
        return updateService.execute(new UpdateTourCommand(id, tour));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return deleteService.execute(id);
    }
}
