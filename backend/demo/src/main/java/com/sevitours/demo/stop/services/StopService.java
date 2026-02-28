package com.sevitours.demo.stop.services;

import com.sevitours.demo.common.ItemNotFound;
import com.sevitours.demo.stop.model.Stop;
import com.sevitours.demo.stop.model.StopDto;
import com.sevitours.demo.stop.model.StopMapper;
import com.sevitours.demo.stop.repo.StopRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class StopService {

    private final StopRepo stopRepo;
    private final StopMapper stopMapper;

    public StopService(StopRepo stopRepo,
                       StopMapper stopMapper) {
        this.stopRepo = stopRepo;
        this.stopMapper = stopMapper;
    }

    public ResponseEntity<StopDto> create(@RequestBody Stop stop) {
        Stop savedStop = stopRepo.save(stop);
        return ResponseEntity.status(HttpStatus.CREATED).body(new StopDto(savedStop));
    }

    public ResponseEntity<StopDto> update(UpdateStopCommand command) {
        Optional<Stop> optionalStop = stopRepo.findById(command.getId());
        if(optionalStop.isPresent()) {
            Stop stop = optionalStop.get();
            stop.setId(command.getId());
            stopRepo.save(stop);
            return ResponseEntity.status(HttpStatus.OK).body(new StopDto(stop));
        }

        throw new ItemNotFound("Stop");
    }

    public ResponseEntity<Void> delete(UUID id) {
        Optional<Stop> optionalStop = stopRepo.findById(id);
        if(optionalStop.isPresent()) {
            stopRepo.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new ItemNotFound("Stop");
    }

    public ResponseEntity<List<StopDto>> findAll() {
        List<StopDto> stopDtos = stopRepo.findAll()
                .stream()
                .map(stopMapper::toDto)
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(stopDtos);
    }

    public ResponseEntity<StopDto> findById(UUID id) {
        Optional<Stop> optionalStop = stopRepo.findById(id);
        if(optionalStop.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(stopMapper.toDto(optionalStop.get()));
        }
        throw new ItemNotFound("Stop");
    }

}
