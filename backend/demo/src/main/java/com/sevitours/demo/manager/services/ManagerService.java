package com.sevitours.demo.manager.services;

import com.sevitours.demo.common.ItemNotFound;
import com.sevitours.demo.manager.model.Manager;
import com.sevitours.demo.manager.model.ManagerDto;
import com.sevitours.demo.manager.model.ManagerMapper;
import com.sevitours.demo.manager.repo.ManagerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    public ManagerService(ManagerRepository managerRepository,
                          ManagerMapper managerMapper) {
        this.managerRepository = managerRepository;
        this.managerMapper = managerMapper;
    }

    public ResponseEntity<ManagerDto> create(Manager manager) {
        Manager saved = managerRepository.save(manager);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ManagerDto(saved));
    }

    public ResponseEntity<Void> delete(UUID id) {
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isPresent()) {
            managerRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new ItemNotFound("District");
    }

    public ResponseEntity<List<ManagerDto>> getAll() {
        List<ManagerDto> dtos = managerRepository.findAll()
                .stream()
                .map(managerMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    public ResponseEntity<ManagerDto> getById(UUID id) {
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isPresent()) {
            return ResponseEntity.ok(managerMapper.toDto(manager.get()));
        }
        throw new ItemNotFound("District");
    }

    public ResponseEntity<ManagerDto> update(UpdateManagerCommand command) {
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
