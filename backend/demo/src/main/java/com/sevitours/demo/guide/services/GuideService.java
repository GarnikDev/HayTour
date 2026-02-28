package com.sevitours.demo.guide.services;

import com.sevitours.demo.common.ItemNotFound;
import com.sevitours.demo.guide.model.Guide;
import com.sevitours.demo.guide.model.GuideDto;
import com.sevitours.demo.guide.model.GuideMapper;
import com.sevitours.demo.guide.repo.GuideRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class GuideService {

    private final GuideRepository guideRepository;
    private final GuideMapper guideMapper;

    public GuideService(GuideRepository guideRepository, GuideMapper guideMapper) {
        this.guideRepository = guideRepository;
        this.guideMapper = guideMapper;
    }

    public ResponseEntity<GuideDto> create(Guide guide) {
        Guide savedGuide = guideRepository.save(guide);
        return ResponseEntity.status(HttpStatus.CREATED).body(new GuideDto(savedGuide));
    }

    public ResponseEntity<Void> delete(UUID id) {
        Optional<Guide> optionalGuide = guideRepository.findById(id);
        if (optionalGuide.isPresent()) {
            guideRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new ItemNotFound("District");
    }

    public ResponseEntity<List<GuideDto>> getAll() {
        List<GuideDto> guideDtos = guideRepository.findAll()
                .stream()
                .map(guideMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(guideDtos);
    }

    public ResponseEntity<GuideDto> getById(UUID id) {
        Optional<Guide> guide = guideRepository.findById(id);
        if (guide.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(guideMapper.toDto(guide.get()));
        }
        throw new ItemNotFound("District");
    }

    public ResponseEntity<GuideDto> update(UpdateGuideCommand command) {
        Optional<Guide> optionalGuide = guideRepository.findById(command.getId());
        if (optionalGuide.isPresent()) {
            Guide guide = command.getGuide();
            guide.setId(command.getId());
            guideRepository.save(guide);
            return ResponseEntity.ok(new GuideDto(guide));
        }
        throw new ItemNotFound("District");
    }

}
