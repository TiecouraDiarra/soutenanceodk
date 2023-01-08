package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.Autoevaluation;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.services.AutoevaluationService;
import SoutenanceBackend.soutenance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("autoevaluation")
public class AutoevaluationController {

    @Autowired
    private AutoevaluationService autoevaluationService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter")
    public Object Ajouter(@RequestBody Autoevaluation autoevaluation){

        // LOG.info("Ajouter avec succès");
        return autoevaluationService.Ajouter(autoevaluation);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficher")
    public List<Autoevaluation> Afficher(){
        return autoevaluationService.Afficher();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/modifier"})
    public String ModierUser(@RequestBody Autoevaluation autoevaluation){
        autoevaluationService.Modifier(autoevaluation);
        return "Modification reussie avec succès";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_autoevaluation}")
    public String Supprimer(@PathVariable("id_autoevaluation") Long id_autoevaluation){

        //LOG.info("Suppression reussie");
        return autoevaluationService.Supprimer(id_autoevaluation);
    }
}
