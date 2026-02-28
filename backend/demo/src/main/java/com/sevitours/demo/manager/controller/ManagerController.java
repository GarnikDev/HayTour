package com.sevitours.demo.manager.controller;

import com.sevitours.demo.manager.model.Manager;
import com.sevitours.demo.manager.model.ManagerDto;
import com.sevitours.demo.manager.services.UpdateManagerCommand;
import com.sevitours.demo.manager.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/managers")
public class ManagerController {

    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping("/manager")
    public ResponseEntity<ManagerDto> create(@RequestBody Manager manager) {
        return managerService.create(manager);
    }

    @GetMapping("/view")
    public ResponseEntity<List<ManagerDto>> getAll() {
        return managerService.getAll();
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<ManagerDto> getById(@PathVariable UUID id) {
        return managerService.getById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ManagerDto> update(@PathVariable UUID id, @RequestBody Manager manager) {
        return managerService.update(new UpdateManagerCommand(id, manager));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return managerService.delete(id);
    }
}
