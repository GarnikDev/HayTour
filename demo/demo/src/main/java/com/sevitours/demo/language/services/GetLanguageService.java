package com.sevitours.demo.language.services;

import com.sevitours.demo.Query;
import com.sevitours.demo.language.LanguageDto;
import com.sevitours.demo.language.LanguageMapper;
import com.sevitours.demo.language.LanguageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GetLanguageService implements Query<Void, List<LanguageDto>> {

    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    public GetLanguageService(LanguageRepository languageRepository, LanguageMapper languageMapper) {
        this.languageRepository = languageRepository;
        this.languageMapper = languageMapper;
    }

    @Override
    public ResponseEntity<List<LanguageDto>> execute(Void input){

        List<LanguageDto> languageDtos = languageRepository.findAll()
                .stream()
                .map(languageMapper::toDto)
                .toList();

        return ResponseEntity.status(HttpStatus.OK).body(languageDtos);
    }
}
