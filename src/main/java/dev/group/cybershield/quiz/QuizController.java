package dev.group.cybershield.quiz;

import dev.group.cybershield.entity.Questions;
import dev.group.cybershield.repository.QuestionRepo;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@Transactional
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private QuestionRepo questionRepo;

    @GetMapping("/v1.0/getQuestions")
    public Questions getQuestions(){
        Optional<Questions> questionData = questionRepo.findById(1L);
        log.info("fetched data from database " + questionData.get());
        return questionData.get();
    }
}
