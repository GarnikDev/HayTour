package com.sevitours.demo.guide.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.guide.Guide;
import com.sevitours.demo.guide.GuideRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteGuideService implements Command<Integer, Void> {

    private final GuideRepository guideRepository;

    public DeleteGuideService(GuideRepository guideRepository) {
        this.guideRepository = guideRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer id) {
        Optional<Guide> optionalGuide = guideRepository.findById(id);
        if (optionalGuide.isPresent()) {
            guideRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return null;
    }
}
