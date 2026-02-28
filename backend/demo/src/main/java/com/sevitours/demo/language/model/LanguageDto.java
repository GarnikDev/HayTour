package com.sevitours.demo.language.model;

import lombok.Data;

import java.util.UUID;

@Data
public class LanguageDto {
    private UUID id;
    private String languageCode;

    public LanguageDto(Language language) {
        if(language != null){
            this.id = language.getId();
            this.languageCode = language.getLanguageCode();
        }
    }
}
