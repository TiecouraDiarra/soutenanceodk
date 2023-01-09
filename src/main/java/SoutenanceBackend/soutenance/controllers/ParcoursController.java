package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.Metier;
import SoutenanceBackend.soutenance.Models.Parcours;
import SoutenanceBackend.soutenance.services.ParcoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("parcours")
public class ParcoursController {

    @Autowired
    private ParcoursService parcoursService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficher")
    public List<Parcours> Afficher(){
        return parcoursService.Afficher();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/modifier/{id_niveauparcours}"})
    public String Modifier(@RequestBody Parcours parcours, @PathVariable("id_niveauparcours") Long id_niveauparcours){
        parcoursService.Modifier(parcours);
        return "Modification reussie avec succès";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_parcours}")
    public String Supprimer(@PathVariable("id_parcours") Long id_parcours){

        //LOG.info("Suppression reussie");
        return parcoursService.Supprimer(id_parcours);
    }
}
