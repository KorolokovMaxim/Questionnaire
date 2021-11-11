package com.example.Questionnaire.repository;

import com.example.Questionnaire.entity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswersRepository extends JpaRepository<Answers,Long> {

    Answers findByName(String name);

}
