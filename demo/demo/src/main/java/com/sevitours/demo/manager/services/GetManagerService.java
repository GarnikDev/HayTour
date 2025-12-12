package com.sevitours.demo.manager.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.manager.ManagerDto;
import com.sevitours.demo.manager.ManagerMapper;
import com.sevitours.demo.manager.ManagerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.geom.RectangularShape;
import java.util.List;

@Service
public class GetManagerService implements Query<Void, List<ManagerDto>> {

    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;

    public GetManagerService(ManagerRepository managerRepository,
                             ManagerMapper managerMapper) {
        this.managerRepository = managerRepository;
        this.managerMapper = managerMapper;
    }

    @Override
    public ResponseEntity<List<ManagerDto>> execute(Void input){

        List<ManagerDto> managerDtos = managerRepository.findAll()
                .stream()
                .map(managerMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(managerDtos);
    }

}
