package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.DomaineProf;
import SoutenanceBackend.soutenance.Models.SerieLycee;
import SoutenanceBackend.soutenance.Repository.DomaineProfRepository;
import SoutenanceBackend.soutenance.Repository.SerieLyceeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8100"}, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("domaineprof")
public class DomaineProfController {
    @Autowired
    private DomaineProfRepository domaineProfRepository;
    @GetMapping("/afficher")
    public List<DomaineProf> Afficher(){
        return domaineProfRepository.findAll();
    }

    //AJOUTER UN TYPE
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter")
    public Object Ajouter(@RequestBody DomaineProf domaineProf){
        // LOG.info("Ajouter avec succès");
        return domaineProfRepository.save(domaineProf);
    }

    //SUPPRIMER UN TYPE
    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_domaine}")
    public String Supprimer(@PathVariable("id_domaine") Long id_domaine){
        domaineProfRepository.deleteById(id_domaine);
        return "Supprimer avec succès";
    }
}
