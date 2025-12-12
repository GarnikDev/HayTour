package com.sevitours.demo.language;

import com.sevitours.demo.language.services.GetLanguageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageController {

    private final GetLanguageService getLanguageService;

    public LanguageController(GetLanguageService getLanguageService) {
        this.getLanguageService = getLanguageService;
    }

    @GetMapping
    public ResponseEntity<List<LanguageDto>> getAllLanguages(){
        return getLanguageService.execute(null);
    }
}
