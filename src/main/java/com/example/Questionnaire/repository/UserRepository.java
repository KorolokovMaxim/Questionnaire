package com.example.Questionnaire.repository;

import com.example.Questionnaire.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);

}
