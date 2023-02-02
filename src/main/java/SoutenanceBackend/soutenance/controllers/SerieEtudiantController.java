package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.Question;
import SoutenanceBackend.soutenance.Models.SerieLycee;
import SoutenanceBackend.soutenance.Repository.SerieLyceeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins ="http://localhost:8100", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("serieetudiant")
public class SerieEtudiantController {
    @Autowired
    private SerieLyceeRepository serieLyceeRepository;
    @GetMapping("/afficher")
    public List<SerieLycee> Afficher(){
        return serieLyceeRepository.findAll();
    }
}
