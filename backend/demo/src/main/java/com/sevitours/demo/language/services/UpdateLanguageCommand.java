package com.sevitours.demo.language.services;

import com.sevitours.demo.language.model.Language;
import lombok.Getter;

import java.util.UUID;

@Getter
public class UpdateLanguageCommand {
    private UUID id;
    private Language language;

    public UpdateLanguageCommand(UUID id, Language language) {
        this.id = id;
        this.language = language;
    }
}