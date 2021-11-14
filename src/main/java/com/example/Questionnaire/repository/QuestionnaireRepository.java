package com.example.Questionnaire.repository;

import com.example.Questionnaire.entity.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionnaireRepository  extends JpaRepository<Questionnaire, Long> {


    Questionnaire findByName(String name);

    Questionnaire findById(long id);

}
