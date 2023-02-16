package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.*;
import SoutenanceBackend.soutenance.Repository.*;
import SoutenanceBackend.soutenance.services.AutoevaluationService;
import SoutenanceBackend.soutenance.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ={"http://localhost:4200", "http://localhost:8100"}, maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AutoevaluationRepository autoevaluationRepository;

    @Autowired
    private TypeMatiereRepository typeMatiereRepository;

    @Autowired
    private TypeQuestionRepository typeQuestionRepository;


    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter/{id_type}/{id_typemat}")
    public Object Ajouter(@PathVariable("id_type") Long id_type, @PathVariable("id_typemat") Long id_typemat, @Param("question1") String question1){

        // LOG.info("Ajouter avec succès");
        /*Autoevaluation autoevaluation = autoevaluationRepository.getReferenceById(id_auto);
        question.setAutoevaluation(autoevaluation);
        question.getAutoevaluation();*/
        Question question = new Question();
        question.setQuestion(question1);
        TypeQuestion typeQuestion = typeQuestionRepository.getReferenceById(id_type);
        question.setTypeQuestion(typeQuestion);
        TypeMatiere typeMatiere = typeMatiereRepository.getReferenceById(id_typemat);
        question.setTypeMatiere(typeMatiere);
        questionService.Ajouter(question);
        return "Question ajoutée avec succès";
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficher")
    public List<Question> Afficher(){
        return questionService.Afficher();
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/modifier/{id_auto}"})
    public String Modifier(@RequestBody Question question, @PathVariable("id_auto") Long id_auto){
        questionService.Modifier(question);
        return "Modification reussie avec succès";
    }

    @PutMapping("/modifierquestion/{id_question}")
    public Question update(@PathVariable Long id_question, @RequestBody Question question){
        return questionService.modifier(id_question, question);
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_question}")
    public String Supprimer(@PathVariable("id_question") Long id_question){

        //LOG.info("Suppression reussie");
        return questionService.Supprimer(id_question);
    }

    //LES QUESTIONS POUR LES ELEVES
    @GetMapping("/afficherQuestionEleve")
    public List<Question> AfficherQuestionEleve(){
        return questionRepository.Questioneleve();
    }

    //LES QUESTIONS POUR LES PROFESSIONNELS(DOMAINE)
    @GetMapping("/afficherQuestionProfe")
    public List<Question> AfficherQuestionProfe(){
        return questionRepository.Questionprofessionnel();
    }

    //LES QUESTIONS POUR LES PROFESSIONNELS(FILIERE)
    @GetMapping("/afficherQuestionProfeFiliere")
    public List<Question> AfficherQuestionProfeFiliere(){
        return questionRepository.QuestionprofessionnelFiliere();
    }

    //LA METHODE PERMETTANT D'AFFICHER LES QUESTIONS DES ETUDIANTS QUI ONT FAIT LA TSECO
    @GetMapping("/afficherQuestionTseco")
    public List<Question> AfficherQuestionTseco(){
        return questionRepository.QuestionTSECO();
    }

    //LA METHODE PERMETTANT D'AFFICHER LES QUESTIONS DES ETUDIANTS QUI ONT FAIT LA TSE
    @GetMapping("/afficherQuestionTse")
    public List<Question> AfficherQuestionTse(){
        return questionRepository.QuestionTSE();
    }

    //LA METHODE PERMETTANT D'AFFICHER LES QUESTIONS DES ETUDIANTS QUI ONT FAIT LA TSEXP
    @GetMapping("/afficherQuestionTsexp")
    public List<Question> AfficherQuestionTsexp(){
        return questionRepository.QuestionTSEXP();
    }

    //LA METHODE PERMETTANT D'AFFICHER LES QUESTIONS DES ETUDIANTS QUI ONT FAIT LA TLL
    @GetMapping("/afficherQuestionTll")
    public List<Question> AfficherQuestionTll(){
        return questionRepository.QuestionTLL();
    }

    //LA METHODE PERMETTANT D'AFFICHER LES QUESTIONS DES ETUDIANTS QUI ONT FAIT LA TAL
    @GetMapping("/afficherQuestionTal")
    public List<Question> AfficherQuestionTal(){
        return questionRepository.QuestionTAL();
    }

    //LA METHODE PERMETTANT D'AFFICHER LES QUESTIONS DES ETUDIANTS QUI ONT FAIT LA TSS
    @GetMapping("/afficherQuestionTss")
    public List<Question> AfficherQuestionTss(){
        return questionRepository.QuestionTSS();
    }
}
