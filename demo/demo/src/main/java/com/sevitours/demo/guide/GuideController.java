package com.sevitours.demo.guide;

import com.sevitours.demo.guide.services.GetGuideService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/guides")
public class GuideController {

    private final GetGuideService getGuideService;

    public GuideController(GetGuideService getGuideService) {
        this.getGuideService = getGuideService;
    }

    @GetMapping
    public ResponseEntity<List<GuideDto>> getAllGuides(){
        return getGuideService.execute(null);
    }
}
