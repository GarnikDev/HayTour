package com.sevitours.demo.guide.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.common.ItemNotFound;
import com.sevitours.demo.guide.model.Guide;
import com.sevitours.demo.guide.model.GuideDto;
import com.sevitours.demo.guide.repo.GuideRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateGuideService implements Command<UpdateGuideCommand, GuideDto> {

    private final GuideRepository guideRepository;

    public UpdateGuideService(GuideRepository guideRepository) {
        this.guideRepository = guideRepository;
    }

    @Override
    public ResponseEntity<GuideDto> execute(UpdateGuideCommand command) {
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
