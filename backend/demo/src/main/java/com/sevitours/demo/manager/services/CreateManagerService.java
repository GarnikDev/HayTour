package com.sevitours.demo.manager.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.manager.model.Manager;
import com.sevitours.demo.manager.model.ManagerDto;
import com.sevitours.demo.manager.repo.ManagerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateManagerService implements Command<Manager, ManagerDto> {

    private final ManagerRepository managerRepository;

    public CreateManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public ResponseEntity<ManagerDto> execute(Manager manager) {
        Manager saved = managerRepository.save(manager);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ManagerDto(saved));
    }
}
