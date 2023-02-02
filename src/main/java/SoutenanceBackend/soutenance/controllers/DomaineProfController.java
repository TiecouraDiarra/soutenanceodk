package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.DomaineProf;
import SoutenanceBackend.soutenance.Models.SerieLycee;
import SoutenanceBackend.soutenance.Repository.DomaineProfRepository;
import SoutenanceBackend.soutenance.Repository.SerieLyceeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins ="http://localhost:8100", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("domaineprof")
public class DomaineProfController {
    @Autowired
    private DomaineProfRepository domaineProfRepository;
    @GetMapping("/afficher")
    public List<DomaineProf> Afficher(){
        return domaineProfRepository.findAll();
    }
}
