package com.sevitours.demo.manager.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.common.ItemNotFound;
import com.sevitours.demo.manager.model.Manager;
import com.sevitours.demo.manager.model.ManagerDto;
import com.sevitours.demo.manager.repo.ManagerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateManagerService implements Command<UpdateManagerCommand, ManagerDto> {

    private final ManagerRepository managerRepository;

    public UpdateManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public ResponseEntity<ManagerDto> execute(UpdateManagerCommand command) {
        Optional<Manager> optional = managerRepository.findById(command.getId());
        if (optional.isPresent()) {
            Manager manager = command.getManager();
            manager.setId(command.getId());
            managerRepository.save(manager);
            return ResponseEntity.ok(new ManagerDto(manager));
        }
        throw new ItemNotFound("Rental");
    }
}
