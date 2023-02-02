package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.MatiereQuestion;
import SoutenanceBackend.soutenance.Models.Niveauparcours;
import SoutenanceBackend.soutenance.Models.TypeMatiere;
import SoutenanceBackend.soutenance.services.MatiereQuestionService;
import SoutenanceBackend.soutenance.services.NiveauParcoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins ="http://localhost:8100", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("matierequestion")
public class MatiereQuestionController {

    @Autowired
    private MatiereQuestionService matiereQuestionService;


    //AJOUTER UNE MATIERE
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter/{id_type}")
    public Object Ajouter(@PathVariable("id_type") TypeMatiere id_type, @RequestBody MatiereQuestion matiereQuestion){

        matiereQuestion.setTypematiere(id_type);
        return matiereQuestionService.Ajouter(matiereQuestion);
    }

    //AFFICHER LES MATIERES
    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficher")
    public List<MatiereQuestion> Afficher(){
        return matiereQuestionService.Afficher();
    }

    //MODIFIER UNE MATIERE
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/modifier"})
    public String Modifier(@RequestBody MatiereQuestion matiereQuestion){

        matiereQuestionService.Modifier(matiereQuestion);
        // LOG.info("Modification reussie avec succès");
        return "Modification reussie avec succès";
    }


    //SUPPRIMER UNE MATIERE
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_matierequestion}")
    public String Supprimer(@PathVariable("id_matierequestion") Long id_matierequestion){

        //LOG.info("Suppression reussie");
        return matiereQuestionService.Supprimer(id_matierequestion);
    }
}
