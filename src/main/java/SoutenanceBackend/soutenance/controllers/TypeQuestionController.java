package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.Niveauparcours;
import SoutenanceBackend.soutenance.Models.TypeQuestion;
import SoutenanceBackend.soutenance.services.NiveauParcoursService;
import SoutenanceBackend.soutenance.services.TypeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("typequestion")
public class TypeQuestionController {
    @Autowired
    private TypeQuestionService typeQuestionService;



    //AJOUTER UN TYPE
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter")
    public Object Ajouter(@RequestBody TypeQuestion typeQuestion){

        // LOG.info("Ajouter avec succès");
        return typeQuestionService.Ajouter(typeQuestion);
    }

    //AFFICHER LES TYPES
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficher")
    public List<TypeQuestion> Afficher(){
        return typeQuestionService.Afficher();
    }

    //MODIFIER UN TYPE
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/modifier"})
    public String Modifier(@RequestBody TypeQuestion typeQuestion){

        typeQuestionService.Modifier(typeQuestion);
        // LOG.info("Modification reussie avec succès");
        return "Modification reussie avec succès";
    }


    //SUPPRIMER UN TYPE
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_typequestion}")
    public String Supprimer(@PathVariable("id_typequestion") Long id_typequestion){

        //LOG.info("Suppression reussie");
        return typeQuestionService.Supprimer(id_typequestion);
    }
}
