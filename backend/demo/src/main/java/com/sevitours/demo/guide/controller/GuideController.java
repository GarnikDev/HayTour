package com.sevitours.demo.guide.controller;

import com.sevitours.demo.guide.services.UpdateGuideCommand;
import com.sevitours.demo.guide.model.Guide;
import com.sevitours.demo.guide.model.GuideDto;
import com.sevitours.demo.guide.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guides")
public class GuideController {

    private final CreateGuideService createGuideService;
    private final GetGuideService getGuideService;
    private final UpdateGuideService updateGuideService;
    private final DeleteGuideService deleteGuideService;

    public GuideController(CreateGuideService createGuideService,
                           GetGuideService getGuideService,
                           UpdateGuideService updateGuideService,
                           DeleteGuideService deleteGuideService) {
        this.createGuideService = createGuideService;
        this.getGuideService = getGuideService;
        this.updateGuideService = updateGuideService;
        this.deleteGuideService = deleteGuideService;
    }

    @PostMapping("/guide")
    public ResponseEntity<GuideDto> createGuide(@RequestBody Guide guide) {
        return createGuideService.execute(guide);
    }

    @GetMapping("/view")
    public ResponseEntity<List<GuideDto>> getAllGuides() {
        Void input = null;
        return getGuideService.execute(input);
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<GuideDto> getGuideById(@PathVariable Integer id) {
        return getGuideService.execute(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<GuideDto> updateGuide(@PathVariable Integer id, @RequestBody Guide guide) {
        return updateGuideService.execute(new UpdateGuideCommand(id, guide));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGuide(@PathVariable Integer id) {
        return deleteGuideService.execute(id);
    }
}
