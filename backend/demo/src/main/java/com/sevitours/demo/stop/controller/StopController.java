package com.sevitours.demo.stop.controller;

import com.sevitours.demo.stop.model.Stop;
import com.sevitours.demo.stop.model.StopDto;
import com.sevitours.demo.stop.services.StopService;
import com.sevitours.demo.stop.services.UpdateStopCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stops")
public class StopController {

    private final StopService stopService;

    public StopController(StopService stopService) {
        this.stopService = stopService;
    }

    @PostMapping("/stop")
    public ResponseEntity<StopDto> create(@RequestBody Stop stop) {
        return stopService.create(stop);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<StopDto> getById(@PathVariable UUID id) {
        return stopService.findById(id);
    }

    @GetMapping("/view")
    public ResponseEntity<List<StopDto>> getAll() {
        return stopService.findAll();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<StopDto> update(@PathVariable UUID id, @RequestBody Stop stop) {
        return stopService.update(new UpdateStopCommand(id, stop));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return stopService.delete(id);
    }
}
