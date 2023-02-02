package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.Matiere;
import SoutenanceBackend.soutenance.Models.Metier;
import SoutenanceBackend.soutenance.Models.Niveauparcours;
import SoutenanceBackend.soutenance.Models.Parcours;
import SoutenanceBackend.soutenance.Repository.NiveauParcoursRepository;
import SoutenanceBackend.soutenance.Repository.ParcoursRepository;
import SoutenanceBackend.soutenance.image.ConfigImage;
import SoutenanceBackend.soutenance.services.ParcoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins ="http://localhost:8100", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("parcours")
public class ParcoursController {

    @Autowired
    private ParcoursService parcoursService;

    @Autowired
    private ParcoursRepository parcoursRepository;

    @Autowired
    private NiveauParcoursRepository niveauParcoursRepository;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter/{id_niveauparcours}")
    public Object Ajouter(@Param("nomparcours") String nomparcours, @Param("description") String description,
                          @Param("titre") String titre,
                          @Param("conseil") String conseil,
                          @Param("duree") String duree,
                          @Param("admission") String admission, @Param("filiere") String filiere,
                          @Param("imageparcours") MultipartFile imageparcours, @PathVariable("id_niveauparcours") Long id_niveauparcours) throws IOException {

        Parcours parcours = new Parcours();
        parcours.setNomparcours(nomparcours);
        parcours.setDescription(description);
        parcours.setTitre(titre);
        parcours.setConseil(conseil);
        parcours.setDuree(duree);
        parcours.setAdmission(admission);
        parcours.setFiliere(filiere);

        String photo = StringUtils.cleanPath(imageparcours.getOriginalFilename());
        parcours.setImageparcours(photo);
        String uploaDir = "C:\\Users\\tddiarra\\Downloads\\Soutenance\\Front\\FrontCarriPlan\\src\\assets\\imgs\\parcours";
        ConfigImage.saveimg(uploaDir, photo, imageparcours);


        // LOG.info("Ajouter avec succès");
        Niveauparcours niveauparcours = niveauParcoursRepository.getReferenceById(id_niveauparcours);
        parcours.setNiveauparcours(niveauparcours);

        /*List<Matiere> matieres = new ArrayList<>();
        parcours.setMatieres(matieres);

        List<Metier> metiers = new ArrayList<>();
        metiers.add(idmetier);
        parcours.setMetiers(metiers);*/

        parcoursService.Ajouter(parcours);
        return "Parcours ajouté avec succès";
    }


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

    //LES PARCOURS DU LYCEE
    //@PreAuthorize("hasRole('ELEVE') or hasRole('ADMIN') or hasRole('ETUDIANT') or hasRole('PROFESSIONNEL')")
    @GetMapping("/afficherParcoursLycee")
    public List<Parcours> AfficherParcoursLycee(){
        return parcoursRepository.ParcoursLycee();
    }

    //LES PARCOURS DU PROFESSIONNEL
    @GetMapping("/afficherParcoursProfessionnel")
    public List<Parcours> AfficherParcoursProfessionnel(){
        return parcoursRepository.ParcoursProfessionnel();
    }

    //LES PARCOURS DE L'UNIVERSITE
    @GetMapping("/afficherParcoursUniversite")
    public List<Parcours> AfficherParcoursUniversite(){
        return parcoursRepository.ParcoursUniversite();
    }

    //RECUPERER ID D'UN PARCOURS
    @GetMapping("/RecupererIdParcours/{IdParcours}")
    public Parcours RetrouverIdParcours(@PathVariable Long IdParcours){
        return parcoursService.RecupererIdParcours(IdParcours);
    }

    @GetMapping("/RecupererIdParcoursMe/{IdParcours}")
    public List<Metier> RetrouverIdParcoursParMetier(@PathVariable Long IdParcours){
        Parcours parcours = parcoursService.RecupererIdParcours(IdParcours);
        return parcours.getMetiers();
    }

    @GetMapping("/RecupererIdParcoursMatiere/{IdParcours}")
    public List<Matiere> RetrouverIdParcoursParMatiere(@PathVariable Long IdParcours){
        Parcours parcours = parcoursService.RecupererIdParcours(IdParcours);
        return parcours.getMatieres();
    }

    //AFFICHER METIER PAR PARCOURS
    /*@GetMapping("/afficherMetierParcours/{IdParcours}")
    public List<Metier> AfficherMetierParcours(@PathVariable Long IdParcours){
        return parcoursRepository.getReferenceById(IdParcours);
    }*/
}
