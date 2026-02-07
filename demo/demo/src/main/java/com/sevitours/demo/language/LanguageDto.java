package com.sevitours.demo.language;

import lombok.Data;

@Data
public class LanguageDto {
    private Integer id;
    private String languageCode;

    public LanguageDto(Language language) {
        if(language != null){
            this.id = language.getId();
            this.languageCode = language.getLanguageCode();
        }
    }
}
