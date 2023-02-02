package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.Matiere;
import SoutenanceBackend.soutenance.Models.Metier;
import SoutenanceBackend.soutenance.Models.Parcours;
import SoutenanceBackend.soutenance.Repository.ParcoursRepository;
import SoutenanceBackend.soutenance.image.ConfigImage;
import SoutenanceBackend.soutenance.services.MetierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins ="http://localhost:8100", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("metiers")
public class MetierController {
    @Autowired
    private ParcoursRepository parcoursRepository;

    @Autowired
    private MetierService metierService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter/{id_parcours}")
    public Object Ajouter(@Param("nommetier") String nommetier, @Param("descriptionmetier") String descriptionmetier,
                          @Param("avantage") String avantage,
                          @Param("imagemetier") MultipartFile imagemetier, @PathVariable("id_parcours") Long id_parcours) throws IOException {

        Metier metier = new Metier();
        metier.setNommetier(nommetier);
        metier.setDescriptionmetier(descriptionmetier);
        metier.setAvantage(avantage);

        String photo = StringUtils.cleanPath(imagemetier.getOriginalFilename());
        metier.setImagemetier(photo);
        String uploaDir = "C:\\Users\\tddiarra\\Downloads\\Soutenance\\Front\\FrontCarriPlan\\src\\assets\\imgs\\metiers";
        ConfigImage.saveimg(uploaDir, photo, imagemetier);

        // LOG.info("Ajouter avec succès");
        Parcours parcours = parcoursRepository.getReferenceById(id_parcours);

        Set<Parcours> parcours1 = new HashSet<>();
        parcours1.add(parcours);

        metier.setParcours(parcours1);
        metierService.Ajouter(metier);
        return "Metier ajouté avec succès";
    }

    //@PreAuthorize("hasRole('ADMIN')")
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

    //RECUPERER ID D'UN METIER
    @GetMapping("/RecupererIdMetier/{IdMetier}")
    public Metier RetrouverIdParcours(@PathVariable Long IdMetier){
        return metierService.RecupererIdMetier(IdMetier);
    }

}
