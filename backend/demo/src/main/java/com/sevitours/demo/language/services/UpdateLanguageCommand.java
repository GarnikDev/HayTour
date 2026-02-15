package com.sevitours.demo.language.services;

import com.sevitours.demo.language.model.Language;
import lombok.Getter;

@Getter
public class UpdateLanguageCommand {
    private Integer id;
    private Language language;

    public UpdateLanguageCommand(Integer id, Language language) {
        this.id = id;
        this.language = language;
    }
}