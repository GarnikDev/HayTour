package com.sevitours.demo.language.services;

import com.sevitours.demo.Command;
import com.sevitours.demo.language.model.Language;
import com.sevitours.demo.language.repo.LanguageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteLanguageService implements Command<Integer, Void> {

    private final LanguageRepository languageRepository;

    public DeleteLanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public ResponseEntity<Void> execute(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (optionalLanguage.isPresent()) {
            languageRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return null;
    }
}
