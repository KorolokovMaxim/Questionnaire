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

    @GetMapping("/index")
    public String getIndexPage() {
        return "index";
    }


    @GetMapping("/allQuestionnaire")
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
                                  @RequestParam(name = "userID") User user) {
        user.setAnswers(answers);
        System.out.println(user.getAnswers());
        userRepository.save(user);
        return "redirect:/allQuestionnaire";

    }

    @GetMapping("/showMyAnswers/{questionnaire}")
    public String showMyAnswers(@PathVariable Questionnaire questionnaire,
                                @AuthenticationPrincipal User user, Model model) {


        Set<Answers> userAnswers = user.getAnswers();
        List<ViewUserAnswer> viewUserAnswers = new ArrayList<>();

        userAnswers.forEach(userAnswer -> {
            Question question = questionRepository.findByAnswers(userAnswer);
            Optional<ViewUserAnswer> viewUserAnswer = viewUserAnswers.stream()
                    .filter(v -> v.getQuestion() == question).findFirst();

            if (viewUserAnswer.isEmpty()) {
                ViewUserAnswer viewUserAnswer1 = new ViewUserAnswer();

                viewUserAnswer1.setQuestion(question);
                viewUserAnswer1.addAnswer(userAnswer);

                viewUserAnswers.add(viewUserAnswer1);
            } else {
                viewUserAnswer.get().addAnswer(userAnswer);
            }
        });

        model.addAttribute("questionnaire", questionnaire);
        model.addAttribute("viewUserAnswers", viewUserAnswers);
        model.addAttribute("user", user);
        return "showMyAnswers";

    }

}
