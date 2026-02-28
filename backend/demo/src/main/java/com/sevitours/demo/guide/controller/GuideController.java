package com.sevitours.demo.guide.controller;

import com.sevitours.demo.guide.services.UpdateGuideCommand;
import com.sevitours.demo.guide.model.Guide;
import com.sevitours.demo.guide.model.GuideDto;
import com.sevitours.demo.guide.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/guides")
public class GuideController {

    private final GuideService guideService;

    public GuideController(GuideService guideService) {
        this.guideService = guideService;
    }

    @PostMapping("/guide")
    public ResponseEntity<GuideDto> createGuide(@RequestBody Guide guide) {
        return guideService.create(guide);
    }

    @GetMapping("/view")
    public ResponseEntity<List<GuideDto>> getAllGuides() {
        return guideService.getAll();
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<GuideDto> getGuideById(@PathVariable UUID id) {
        return guideService.getById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<GuideDto> updateGuide(@PathVariable UUID id, @RequestBody Guide guide) {
        return guideService.update(new UpdateGuideCommand(id, guide));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGuide(@PathVariable UUID id) {
        return guideService.delete(id);
    }
}
