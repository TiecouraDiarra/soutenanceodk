package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.Autoevaluation;
import SoutenanceBackend.soutenance.Models.Question;
import SoutenanceBackend.soutenance.Repository.AutoevaluationRepository;
import SoutenanceBackend.soutenance.services.AutoevaluationService;
import SoutenanceBackend.soutenance.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AutoevaluationRepository autoevaluationRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter/{id_auto}")
    public Object Ajouter(@RequestBody Question question, @PathVariable("id_auto") Long id_auto){

        // LOG.info("Ajouter avec succès");
        Autoevaluation autoevaluation = autoevaluationRepository.getReferenceById(id_auto);
        question.setAutoevaluation(autoevaluation);
        question.getAutoevaluation();
        questionService.Ajouter(question);
        return "Question ajoutée avec succès";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficher")
    public List<Question> Afficher(){
        return questionService.Afficher();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/modifier/{id_auto}"})
    public String Modifier(@RequestBody Question question, @PathVariable("id_auto") Long id_auto){
        questionService.Modifier(question);
        return "Modification reussie avec succès";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_question}")
    public String Supprimer(@PathVariable("id_question") Long id_question){

        //LOG.info("Suppression reussie");
        return questionService.Supprimer(id_question);
    }
}
