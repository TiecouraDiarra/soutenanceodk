package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.Matiere;
import SoutenanceBackend.soutenance.Models.Metier;
import SoutenanceBackend.soutenance.Models.Parcours;
import SoutenanceBackend.soutenance.Repository.ParcoursRepository;
import SoutenanceBackend.soutenance.services.MetierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("metier")
public class MetierController {
    @Autowired
    private ParcoursRepository parcoursRepository;

    @Autowired
    private MetierService metierService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter/{id_parcours}")
    public Object Ajouter(@RequestBody Metier metier, @PathVariable("id_parcours") Long id_parcours){

        // LOG.info("Ajouter avec succès");
        Parcours parcours = parcoursRepository.getReferenceById(id_parcours);
        metier.setParcours((Set<Parcours>) parcours);
        metier.getParcours();
        metierService.Ajouter(metier);
        return "Metier ajouté avec succès";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficher")
    public List<Metier> Afficher(){
        return metierService.Afficher();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/modifier/{id_parcours}"})
    public String Modifier(@RequestBody Metier metier, @PathVariable("id_parcours") Long id_parcours){
        metierService.Modifier(metier);
        return "Modification reussie avec succès";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_metier}")
    public String Supprimer(@PathVariable("id_metier") Long id_metier){

        //LOG.info("Suppression reussie");
        return metierService.Supprimer(id_metier);
    }


}
