package com.example.Questionnaire.controller;

import com.example.Questionnaire.entity.*;
import com.example.Questionnaire.repository.AnswersRepository;
import com.example.Questionnaire.repository.QuestionRepository;
import com.example.Questionnaire.repository.QuestionnaireRepository;
import com.example.Questionnaire.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.*;


@Controller
public class MainController {

    private final QuestionnaireRepository questionnaireRepository;
    private final QuestionRepository questionRepository;
    private final AnswersRepository answersRepository;
    private final UserRepository userRepository;

    public MainController(QuestionnaireRepository questionnaireRepository, QuestionRepository questionRepository, AnswersRepository answersRepository, UserRepository userRepository, UserRepository userRepository1) {
        this.questionnaireRepository = questionnaireRepository;
        this.questionRepository = questionRepository;
        this.answersRepository = answersRepository;
        this.userRepository = userRepository1;
    }


    @GetMapping("/")
    public String getAllQuestionnaire(Model model) {

        model.addAttribute("questionnaires", questionnaireRepository.findAll());

        return "allQuestionnaires";

    }


    @GetMapping("/questionnaire/{questionnaire}")
    public String getOneQuestionnaire(@PathVariable Questionnaire questionnaire,
                                      @AuthenticationPrincipal User user,
                                      Model model) {

        model.addAttribute("questionnaire", questionnaire);
        List<Question> questionList = questionRepository.findByQuestionnaire(questionnaire);
        model.addAttribute("questions", questionList);
        Map<Question, List<Answers>> questionAnswersMap = new HashMap<>();
        for (Question q : questionList) {

            questionAnswersMap.put(q, answersRepository.findAllByQuestionId(q.getId()));
        }
        model.addAttribute("qam", questionAnswersMap);
        model.addAttribute("user", user);

        return "oneQuestionnaire";
    }

    @PostMapping("/saveUserAnswers")
    public String saveUserAnswers(@RequestParam(name = "queryAnswers[]") Set<Answers> answers,
                                 @AuthenticationPrincipal User user) {


        Set<Answers> userAnswers = user.getAnswers();
        userAnswers.addAll(answers);
        user.setAnswers(userAnswers);
        userRepository.save(user);


        return "redirect:/";
    }

    @GetMapping("/showMyAnswers/{questionnaire}")
    public String showMyAnswers(@PathVariable Questionnaire questionnaire,
                                @AuthenticationPrincipal User user, Model model) {


        Set<Answers> userAnswers = user.getAnswers();
        List<ViewUserAnswer> viewUserAnswers = new ArrayList<>();

        questionnaire.getQuestions().forEach(question -> {
            ViewUserAnswer viewUserAnswer = new ViewUserAnswer();
            viewUserAnswer.setQuestion(question);

            question.getAnswers().forEach(answer -> {
                if (userAnswers.contains(answer)) {
                    viewUserAnswer.addAnswer(answer);
                }
            });

            viewUserAnswers.add(viewUserAnswer);
        });

        model.addAttribute("questionnaire", questionnaire);
        model.addAttribute("viewUserAnswers", viewUserAnswers);
        model.addAttribute("user", user);
        return "showMyAnswers";

    }

}