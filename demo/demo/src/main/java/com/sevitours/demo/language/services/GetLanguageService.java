package com.sevitours.demo.language.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.language.model.Language;
import com.sevitours.demo.language.model.LanguageDto;
import com.sevitours.demo.language.model.LanguageMapper;
import com.sevitours.demo.language.repo.LanguageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GetLanguageService implements Query<Void, List<LanguageDto>> {

    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    public GetLanguageService(LanguageRepository languageRepository,
                              LanguageMapper languageMapper) {
        this.languageRepository = languageRepository;
        this.languageMapper = languageMapper;
    }

    @Override
    public ResponseEntity<List<LanguageDto>> execute(Void input) {
        List<LanguageDto> dtos = languageRepository.findAll()
                .stream()
                .map(languageMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    public ResponseEntity<LanguageDto> execute(Integer id) {
        Optional<Language> language = languageRepository.findById(id);
        if (language.isPresent()) {
            return ResponseEntity.ok(languageMapper.toDto(language.get()));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
}
