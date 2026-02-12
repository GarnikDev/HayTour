package com.sevitours.demo.manager.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.manager.model.Manager;
import com.sevitours.demo.manager.repo.ManagerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteManagerService implements Command<Integer, Void> {

    private final ManagerRepository managerRepository;

    public DeleteManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer id) {
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isPresent()) {
            managerRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return null;
    }
}
