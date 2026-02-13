package com.sevitours.demo.language.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.common.ItemNotFound;
import com.sevitours.demo.language.model.Language;
import com.sevitours.demo.language.model.LanguageDto;
import com.sevitours.demo.language.repo.LanguageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateLanguageService implements Command<UpdateLanguageCommand, LanguageDto> {

    private final LanguageRepository languageRepository;

    public UpdateLanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public ResponseEntity<LanguageDto> execute(UpdateLanguageCommand command) {
        Optional<Language> optionalLanguage = languageRepository.findById(command.getId());
        if (optionalLanguage.isPresent()) {
            Language language = command.getLanguage();
            language.setId(command.getId());
            languageRepository.save(language);
            return ResponseEntity.ok(new LanguageDto(language));
        }
        throw new ItemNotFound("District");
    }
}