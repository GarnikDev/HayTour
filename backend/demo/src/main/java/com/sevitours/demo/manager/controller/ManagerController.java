package com.sevitours.demo.manager.controller;

import com.sevitours.demo.manager.model.Manager;
import com.sevitours.demo.manager.model.ManagerDto;
import com.sevitours.demo.manager.services.UpdateManagerCommand;
import com.sevitours.demo.manager.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/managers")
public class ManagerController {

    private final CreateManagerService createManagerService;
    private final GetManagerService getManagerService;
    private final UpdateManagerService updateManagerService;
    private final DeleteManagerService deleteManagerService;

    public ManagerController(CreateManagerService createManagerService,
                             GetManagerService getManagerService,
                             UpdateManagerService updateManagerService,
                             DeleteManagerService deleteManagerService) {
        this.createManagerService = createManagerService;
        this.getManagerService = getManagerService;
        this.updateManagerService = updateManagerService;
        this.deleteManagerService = deleteManagerService;
    }

    @PostMapping("/manager")
    public ResponseEntity<ManagerDto> create(@RequestBody Manager manager) {
        return createManagerService.execute(manager);
    }

    @GetMapping("/view")
    public ResponseEntity<List<ManagerDto>> getAll() {
        Void input = null;
        return getManagerService.execute(input);
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<ManagerDto> getById(@PathVariable Integer id) {
        return getManagerService.execute(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ManagerDto> update(@PathVariable Integer id, @RequestBody Manager manager) {
        return updateManagerService.execute(new UpdateManagerCommand(id, manager));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        return deleteManagerService.execute(id);
    }
}
