package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.Autoevaluation;
import SoutenanceBackend.soutenance.Models.Question;
import SoutenanceBackend.soutenance.Models.Reponse;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.Repository.AutoevaluationRepository;
import SoutenanceBackend.soutenance.Repository.QuestionRepository;
import SoutenanceBackend.soutenance.Repository.UserRepository;
import SoutenanceBackend.soutenance.services.QuestionService;
import SoutenanceBackend.soutenance.services.ReponseService;
import SoutenanceBackend.soutenance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("reponse")
public class ReponseController {
    @Autowired
    private ReponseService reponseService;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter/{id_question}")
    public Object Ajouter(@RequestBody Reponse reponse, @PathVariable("id_question") Long id_question){

        // LOG.info("Ajouter avec succès");
        Question question = questionRepository.getReferenceById(id_question);
        reponse.setQuestion(question);


        //userService.FatimMethode(userDetails.getId());
        User user = userService.ff();
        reponse.setUtilisateur(user);
        reponseService.Ajouter(reponse);
        return "Reponse ajoutée avec succès";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficher")
    public List<Reponse> Afficher(){
        return reponseService.Afficher();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/modifier/{id_question}"})
    public String Modifier(@RequestBody Reponse reponse, @PathVariable("id_question") Long id_question){
        reponseService.Modifier(reponse);
        return "Modification reussie avec succès";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_reponse}")
    public String Supprimer(@PathVariable("id_reponse") Long id_reponse){

        //LOG.info("Suppression reussie");
        return reponseService.Supprimer(id_reponse);
    }
}
