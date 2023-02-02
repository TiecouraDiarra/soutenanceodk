package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.MatiereQuestion;
import SoutenanceBackend.soutenance.Models.ParametreMatiere;
import SoutenanceBackend.soutenance.Repository.MatiereQuestionRepository;
import SoutenanceBackend.soutenance.services.MatiereQuestionService;
import SoutenanceBackend.soutenance.services.ParamMatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("parametrematiere")
public class ParamMatiereController {
    @Autowired
    private ParamMatiereService paramMatiereService;

    @Autowired
    private MatiereQuestionRepository matiereQuestionRepository;


    //AJOUTER UN PARAMETRE
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter/{id_matques}")
    public Object Ajouter(@Param("valeurPlafond") Long valeurPlafond, @PathVariable("id_matques") Long id_matques){

        ParametreMatiere parametreMatiere = new ParametreMatiere();
        parametreMatiere.setValeurPlafond(valeurPlafond);

        MatiereQuestion matiereQuestion = matiereQuestionRepository.getReferenceById(id_matques);

        parametreMatiere.setMatiereQuestion(matiereQuestion);
        return paramMatiereService.Ajouter(parametreMatiere);
    }

    //AFFICHER LES PARAMETRES
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficher")
    public List<ParametreMatiere> Afficher(){
        return paramMatiereService.Afficher();
    }

    //MODIFIER UN PARAMETRE
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/modifier"})
    public String Modifier(@RequestBody ParametreMatiere parametreMatiere){

        paramMatiereService.Modifier(parametreMatiere);
        // LOG.info("Modification reussie avec succès");
        return "Modification reussie avec succès";
    }


    //SUPPRIMER UN PARAMETRE
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_param}")
    public String Supprimer(@PathVariable("id_param") Long id_param){

        //LOG.info("Suppression reussie");
        return paramMatiereService.Supprimer(id_param);
    }
}
