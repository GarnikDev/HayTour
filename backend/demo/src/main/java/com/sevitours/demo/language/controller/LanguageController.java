package com.sevitours.demo.language.controller;

import com.sevitours.demo.language.services.UpdateLanguageCommand;
import com.sevitours.demo.language.model.Language;
import com.sevitours.demo.language.model.LanguageDto;
import com.sevitours.demo.language.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/languages")
public class LanguageController {

    private final LanguageService languageService;

    public LanguageController(LanguageService languageService) {
        this.languageService = languageService;
    }

    @PostMapping("/language")
    public ResponseEntity<LanguageDto> create(@RequestBody Language language) {
        return languageService.create(language);
    }

    @GetMapping("/view")
    public ResponseEntity<List<LanguageDto>> getAllLanguages() {
        return languageService.getAll();
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<LanguageDto> getById(@PathVariable UUID id) {
        return languageService.getById(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<LanguageDto> update(@PathVariable UUID id, @RequestBody Language language) {
        return languageService.update(new UpdateLanguageCommand(id, language));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        return languageService.delete(id);
    }
}
