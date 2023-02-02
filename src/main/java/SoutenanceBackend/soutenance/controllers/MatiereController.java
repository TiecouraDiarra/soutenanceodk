package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.*;
import SoutenanceBackend.soutenance.Repository.MatiereRepository;
import SoutenanceBackend.soutenance.Repository.ParcoursRepository;
import SoutenanceBackend.soutenance.services.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("matiere")
public class MatiereController {

    @Autowired
    private ParcoursRepository parcoursRepository;

    @Autowired
    private MatiereService matiereService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter/{id_parcours}")
    public Object Ajouter(@RequestBody Matiere matiere, @PathVariable("id_parcours") Long id_parcours){

        // LOG.info("Ajouter avec succès");
        Parcours parcours = parcoursRepository.getReferenceById(id_parcours);

        Set<Parcours> parcours1 = new HashSet<>();
        parcours1.add(parcours);

        matiere.setParcours(parcours1);
        matiereService.Ajouter(matiere);
        return "Matiere ajoutée avec succès";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficher")
    public List<Matiere> Afficher(){
        return matiereService.Afficher();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/modifier/{id_parcours}"})
    public String Modifier(@RequestBody Matiere matiere, @PathVariable("id_parcours") Long id_parcours){
        matiereService.Modifier(matiere);
        return "Modification reussie avec succès";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_matiere}")
    public String Supprimer(@PathVariable("id_matiere") Long id_matiere){

        //LOG.info("Suppression reussie");
        return matiereService.Supprimer(id_matiere);
    }
}
