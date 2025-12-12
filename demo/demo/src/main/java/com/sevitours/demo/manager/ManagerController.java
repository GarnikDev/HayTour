package com.sevitours.demo.manager;

import com.sevitours.demo.manager.services.GetManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/managers")
public class ManagerController {

    private final GetManagerService getManagerService;

    public ManagerController(GetManagerService getManagerService) {
        this.getManagerService = getManagerService;
    }

    @GetMapping
    public ResponseEntity<List<ManagerDto>> getAllManagers(){
        return getManagerService.execute(null);
    }
}
