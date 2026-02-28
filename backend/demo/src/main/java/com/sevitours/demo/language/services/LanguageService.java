package com.sevitours.demo.language.services;

import com.sevitours.demo.common.ItemNotFound;
import com.sevitours.demo.district.repo.DistrictRepository;
import com.sevitours.demo.language.model.Language;
import com.sevitours.demo.language.model.LanguageDto;
import com.sevitours.demo.language.model.LanguageMapper;
import com.sevitours.demo.language.repo.LanguageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    public LanguageService(LanguageRepository languageRepository,
                           LanguageMapper languageMapper) {
        this.languageRepository = languageRepository;
        this.languageMapper = languageMapper;
    }

    public ResponseEntity<LanguageDto> create(Language language) {
        Language savedLanguage = languageRepository.save(language);
        return ResponseEntity.status(HttpStatus.CREATED).body(new LanguageDto(savedLanguage));
    }

    public ResponseEntity<Void> delete(UUID id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isPresent()) {
            languageRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        throw new ItemNotFound("District");
    }

    public ResponseEntity<List<LanguageDto>> getAll() {
        List<LanguageDto> dtos = languageRepository.findAll()
                .stream()
                .map(languageMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    public ResponseEntity<LanguageDto> getById(UUID id) {
        Optional<Language> language = languageRepository.findById(id);
        if (language.isPresent()) {
            return ResponseEntity.ok(languageMapper.toDto(language.get()));
        }
        throw new ItemNotFound("District");
    }

    public ResponseEntity<LanguageDto> update(UpdateLanguageCommand command) {
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
