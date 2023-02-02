package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.*;
import SoutenanceBackend.soutenance.Repository.*;
import SoutenanceBackend.soutenance.services.QuestionService;
import SoutenanceBackend.soutenance.services.ReponseService;
import SoutenanceBackend.soutenance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("reponse")
public class ReponseController {
    /*@Autowired
    private ReponseService reponseService;

    @Autowired
    private ReponseRepository reponseRepository;

    @GetMapping("/TotalScience/{iduser}")
    public Long getNombreTirageUneListe(@PathVariable Long iduser){
        return reponseService.TotaleScience(iduser);
    }*/


}
