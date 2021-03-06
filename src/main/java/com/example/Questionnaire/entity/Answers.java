package com.example.Questionnaire.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "answers")
public class Answers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST ,fetch = FetchType.EAGER)
    private Question question;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_answers",
            joinColumns = @JoinColumn(name = "answers_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<User> users = new HashSet<>();

    public Answers() {
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Answers answers = (Answers) o;
        return id != null && Objects.equals(id, answers.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }


}