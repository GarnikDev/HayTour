package com.sevitours.demo.language.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.language.model.Language;
import com.sevitours.demo.language.model.LanguageDto;
import com.sevitours.demo.language.repo.LanguageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreateLanguageService implements Command<Language, LanguageDto> {

    private final LanguageRepository languageRepository;

    public CreateLanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public ResponseEntity<LanguageDto> execute(Language language) {
        Language savedLanguage = languageRepository.save(language);
        return ResponseEntity.status(HttpStatus.CREATED).body(new LanguageDto(savedLanguage));
    }
}
