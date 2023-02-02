package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.TypeMatiere;
import SoutenanceBackend.soutenance.Models.TypeQuestion;
import SoutenanceBackend.soutenance.Repository.TypeMatiereRepository;
import SoutenanceBackend.soutenance.services.TypeMatiereService;
import SoutenanceBackend.soutenance.services.TypeQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("typematiere")
public class TypeMatiereController {
    @Autowired
    private TypeMatiereService typeMatiereService;



    //AJOUTER UN TYPE
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter")
    public Object Ajouter(@RequestBody TypeMatiere typeMatiere){

        // LOG.info("Ajouter avec succès");
        return typeMatiereService.Ajouter(typeMatiere);
    }
}
