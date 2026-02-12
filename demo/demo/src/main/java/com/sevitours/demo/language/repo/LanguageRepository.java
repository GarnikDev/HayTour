package com.sevitours.demo.language.repo;

import com.sevitours.demo.language.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Integer> {
}
