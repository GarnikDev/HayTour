package com.sevitours.demo.manager.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.manager.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetManagerService implements Query<Void, List<ManagerDto>> {

    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    public GetManagerService(ManagerRepository managerRepository, ManagerMapper managerMapper) {
        this.managerRepository = managerRepository;
        this.managerMapper = managerMapper;
    }

    @Override
    public ResponseEntity<List<ManagerDto>> execute(Void input) {
        List<ManagerDto> dtos = managerRepository.findAll()
                .stream()
                .map(managerMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    public ResponseEntity<ManagerDto> execute(Integer id) {
        Optional<Manager> manager = managerRepository.findById(id);
        if (manager.isPresent()) {
            return ResponseEntity.ok(managerMapper.toDto(manager.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
