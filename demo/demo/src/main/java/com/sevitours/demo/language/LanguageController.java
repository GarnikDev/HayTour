package com.sevitours.demo.language;

import com.sevitours.demo.language.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
public class LanguageController {

    private final CreateLanguageService createLanguageService;
    private final GetLanguageService getLanguageService;
    private final UpdateLanguageService updateLanguageService;
    private final DeleteLanguageService deleteLanguageService;

    public LanguageController(CreateLanguageService createLanguageService,
                              GetLanguageService getLanguageService,
                              UpdateLanguageService updateLanguageService,
                              DeleteLanguageService deleteLanguageService) {
        this.createLanguageService = createLanguageService;
        this.getLanguageService = getLanguageService;
        this.updateLanguageService = updateLanguageService;
        this.deleteLanguageService = deleteLanguageService;
    }

    @PostMapping("/language")
    public ResponseEntity<LanguageDto> createLanguage(@RequestBody Language language) {
        return createLanguageService.execute(language);
    }

    @GetMapping("/view")
    public ResponseEntity<List<LanguageDto>> getAllLanguages() {
        Void input = null;
        return getLanguageService.execute(input);
    }

    @GetMapping("/view/id/{id}")
    public ResponseEntity<LanguageDto> getLanguageById(@PathVariable Integer id) {
        return getLanguageService.execute(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<LanguageDto> updateLanguage(@PathVariable Integer id, @RequestBody Language language) {
        return updateLanguageService.execute(new UpdateLanguageCommand(id, language));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable Integer id) {
        return deleteLanguageService.execute(id);
    }
}
