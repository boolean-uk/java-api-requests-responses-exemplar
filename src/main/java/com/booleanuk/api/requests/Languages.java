package com.booleanuk.api.requests;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/languages")
public class Languages {
    private List<Language> languages = new ArrayList<>(){{
        add(new Language("Java"));
        add(new Language("C#"));
    }};

    @GetMapping
    public List<Language> getAll() {
        return this.languages;
    }

    @GetMapping("/{language}")
    public Language getSpecificLanguage(@PathVariable String language) {
        Language languageToReturn = null;
        for (Language theLanguage: this.languages) {
            if (theLanguage.getName().equals(language)) {
                languageToReturn = theLanguage;
                break;
            }
        }
        return languageToReturn;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Language create(@RequestBody Language language) {
        this.languages.add(language);
        return language;
    }

    @PutMapping("/{languageName}")
    @ResponseStatus(HttpStatus.CREATED)
    public Language update(@PathVariable String languageName, @RequestBody Language language) {
        Language languageToUpdate = null;
        for (int i = 0; i < this.languages.size(); i++) {
            if (this.languages.get(i).getName().equals(languageName)) {
                this.languages.set(i, language);
                languageToUpdate = this.languages.get(i);
                break;
            }
        }
        return languageToUpdate;
    }

    @DeleteMapping("/{language}")
    public Language delete(@PathVariable String language) {
        Language languageToDelete = null;
        boolean languageFound = false;
        for (Language theLanguage : this.languages) {
            if (theLanguage.getName().equals(language)) {
                languageToDelete = theLanguage;
                languageFound = true;
                break;
            }
        }
        if (languageFound) {
            this.languages.remove(languageToDelete);
        }
        return languageToDelete;
    }
}
