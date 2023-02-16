package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.Question;
import SoutenanceBackend.soutenance.Models.SerieLycee;
import SoutenanceBackend.soutenance.Models.TypeQuestion;
import SoutenanceBackend.soutenance.Repository.SerieLyceeRepository;
import SoutenanceBackend.soutenance.services.SerieLyceeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8100"}, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("serieetudiant")
public class SerieEtudiantController {

    @Autowired
    private SerieLyceeService serieLyceeService;
    @Autowired
    private SerieLyceeRepository serieLyceeRepository;
    @GetMapping("/afficher")
    public List<SerieLycee> Afficher(){
        return serieLyceeRepository.findAll();
    }


    //AJOUTER UN TYPE
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter")
    public Object Ajouter(@RequestBody SerieLycee serieLycee){
        // LOG.info("Ajouter avec succès");
        return serieLyceeService.Ajouter(serieLycee);
    }

    //SUPPRIMER UN TYPE
    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_serie}")
    public String Supprimer(@PathVariable("id_serie") Long id_serie){
        serieLyceeRepository.deleteById(id_serie);
        return "Supprimer avec succès";
    }

}
