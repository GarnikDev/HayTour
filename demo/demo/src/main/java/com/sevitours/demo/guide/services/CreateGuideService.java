package com.sevitours.demo.guide.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.guide.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateGuideService implements Command<Guide, GuideDto> {

    private final GuideRepository guideRepository;

    public CreateGuideService(GuideRepository guideRepository) {
        this.guideRepository = guideRepository;
    }

    @Override
    public ResponseEntity<GuideDto> execute(Guide guide) {
        Guide savedGuide = guideRepository.save(guide);
        return ResponseEntity.status(HttpStatus.CREATED).body(new GuideDto(savedGuide));
    }
}
