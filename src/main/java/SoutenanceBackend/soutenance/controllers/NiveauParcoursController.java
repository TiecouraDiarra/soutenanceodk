package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.Autoevaluation;
import SoutenanceBackend.soutenance.Models.Niveauparcours;
import SoutenanceBackend.soutenance.Models.Parcours;
import SoutenanceBackend.soutenance.Models.User;
import SoutenanceBackend.soutenance.services.NiveauParcoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("niveauparcours")
public class NiveauParcoursController {
    @Autowired
    private NiveauParcoursService niveauParcoursService;


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter")
    public Object Ajouter(@RequestBody Niveauparcours niveauparcours){

        // LOG.info("Ajouter avec succès");
        return niveauParcoursService.Ajouter(niveauparcours);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficher")
    public List<Niveauparcours> Afficher(){
        return niveauParcoursService.Afficher();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/modifier"})
    public String Modifier(@RequestBody Niveauparcours niveauparcours){

        niveauParcoursService.Modifier(niveauparcours);
        // LOG.info("Modification reussie avec succès");
        return "Modification reussie avec succès";
    }


    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_niveauparcours}")
    public String Supprimer(@PathVariable("id_niveauparcours") Long id_niveauparcours){

        //LOG.info("Suppression reussie");
        return niveauParcoursService.Supprimer(id_niveauparcours);
    }
}
