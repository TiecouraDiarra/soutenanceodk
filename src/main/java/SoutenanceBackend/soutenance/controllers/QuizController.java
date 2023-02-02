package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.Answer;
import SoutenanceBackend.soutenance.Models.EvaluationResult;
import SoutenanceBackend.soutenance.Models.QuestionProfessionnel;
import SoutenanceBackend.soutenance.Repository.AnswerRepository;
import SoutenanceBackend.soutenance.Repository.QuestionProfRepository;
import SoutenanceBackend.soutenance.services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("quiz")
public class QuizController {
    /*@Autowired
    private QuestionProfRepository questionRepository;

    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private AnswerRepository answerRepository;

    @GetMapping("/questions")
    public ResponseEntity<List<QuestionProfessionnel>> getAllQuestions() {
        List<QuestionProfessionnel> questions = evaluationService.getQuestions();
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }*/

    /*@PostMapping("/answer")
    public ResponseEntity<EvaluationResult> submitAnswer(@RequestBody Answer answer) {
        EvaluationResult result = evaluationService.evaluateAnswers((List<Answer>) answer);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }*/

    /*@PostMapping("/answer")
    public ResponseEntity<EvaluationResult> submitAnswer(@RequestBody Answer answer) {
        EvaluationResult result = evaluationService.evaluateAnswer(answer);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }*/
}
