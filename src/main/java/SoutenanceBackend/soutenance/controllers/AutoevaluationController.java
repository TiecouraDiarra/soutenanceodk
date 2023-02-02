package SoutenanceBackend.soutenance.controllers;

import SoutenanceBackend.soutenance.Models.*;
import SoutenanceBackend.soutenance.Repository.*;
import SoutenanceBackend.soutenance.services.AutoevaluationService;
import SoutenanceBackend.soutenance.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins ="http://localhost:8100", maxAge = 3600, allowCredentials = "true")
@RestController
@RequestMapping("autoevaluation")
public class AutoevaluationController {

    @Autowired
    private AutoevaluationService autoevaluationService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;


    @Autowired
    private IntReponseRepository intReponseRepository;

    @Autowired
    private AutoevaluationRepository autoevaluationRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ReponseRepository reponseRepository;

    @Autowired
    private ParcoursRepository parcoursRepository;

    /*@Autowired
    private ReponseService reponseService;*/

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajouter/{id_user}")
    public Object Ajouter(@RequestBody Autoevaluation autoevaluation, @PathVariable("id_user") Long id_user){

        User user = userRepository.findById(id_user).get();


        autoevaluation.setUtilisateur(user);

        //autoevaluation.setDateauto(new Date());

        //Reponse reponse = new Reponse();
        /*autoevaluation.getReponses().forEach(reponse1 -> {
            if (reponseRepository.findByReponse(reponse1.getReponse()) == null) reponseRepository.save(reponse1);
        });*/
        List<Autoevaluation> a = autoevaluationRepository.findByUtilisateur(user);
        System.out.println(a);
        if (a.size() > 2){
            return "Vous avez atteint le nombre maximum de votre autoevaluation";
        }else {
            return autoevaluationService.Ajouter(autoevaluation);
        }
    }


    /*@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/ajout/{idutil}/{idmatier}/{idtypequestion}")
    public String aj(@RequestBody Reponse reponse, @PathVariable("idutil") Long idutil, @PathVariable("idmatier") Long idmatier, @PathVariable("idtypequestion") Long idtypequestion){

        return autoevaluationService.ARET(reponse,idutil,idmatier,idtypequestion);
    }*/

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficher")
    public List<Autoevaluation> Afficher(){
        return autoevaluationService.Afficher();
    }

    //RECUPERER AUTOEVALUATION PAR ID
    @GetMapping("/RecupererAutoParId/{id_auto}")
    public Optional<Autoevaluation> autoevaluation(@PathVariable("id_auto") Long id_auto){
        return autoevaluationRepository.findById(id_auto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/modifier"})
    public String ModierUser(@RequestBody Autoevaluation autoevaluation){
        autoevaluationService.Modifier(autoevaluation);
        return "Modification reussie avec succès";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/supprimer/{id_autoevaluation}")
    public String Supprimer(@PathVariable("id_autoevaluation") Long id_autoevaluation){

        //LOG.info("Suppression reussie");
        return autoevaluationService.Supprimer(id_autoevaluation);
    }

    @GetMapping("/AfficherAutoUser/{id}")
    public Iterable<Object[]> getToutesListe(@PathVariable Long id){
        return autoevaluationService.IdAutoevaluation(id);
    }

    @GetMapping("/AutoUser/{id_user}")
    public List<Autoevaluation> getAutoUser(@PathVariable("id_user") Long id_user){
        User user = userRepository.findById(id_user).get();
        List<Autoevaluation> autouser = autoevaluationRepository.findByUtilisateur(user);
        /*System.err.println(autouser.size());

        Autoevaluation autoevaluation = autoevaluationRepository.getReferenceById((long) autouser.size());
        System.err.println(autoevaluation.getId());*/
        return autouser;
    }

    //==============================ELEVE=====================================================
    @GetMapping("/TotaleScience/{Id}/{typematiere}/{idauto}")
    public Object getTotaleScience(@PathVariable Long Id, @PathVariable("typematiere") String typematiere,@PathVariable Long idauto){
        return reponseRepository.TotaleScience(Id,idauto, typematiere);
    }

    @GetMapping("/TotaleLettre/{Id}/{typematiere}/{idauto}")
    public Long getTotaleLettre(@PathVariable Long Id, @PathVariable("typematiere") String typematiere, @PathVariable Long idauto){
        return reponseRepository.TotaleLettre(Id,idauto,typematiere);
    }

    //PARCOURS RECENTS DU PARCOURS LYCEE D'UN ELEVE
    @GetMapping("Autorecentelycee/{iduser}")
    public List<Parcours> Autorecente(@PathVariable("iduser") Long iduser){

        List<Autoevaluation> autoevaluation1 = autoevaluationRepository.findLatestByIdUser(iduser);
        Long idautoe = 0L;

        if (autoevaluation1.size() >1){
            for (Autoevaluation autoevaluation2: autoevaluation1){
                System.err.println(autoevaluation2.getId());
                idautoe = autoevaluation2.getId();

            }
        }else {
            for (Autoevaluation autoevaluation2: autoevaluation1){
                idautoe = autoevaluation2.getId();
            }
        }
        /*System.err.println("AUTOEVALUATIONNNNNNNNNNNNNNNNNN"+iduser);
        System.err.println(idautoe);*/
        String typematiere="science";
        String typematiere1="lettre";
        Long TotaleScience = reponseRepository.TotaleScience(iduser, idautoe, typematiere);
        Long TotaleLettre = reponseRepository.TotaleLettre(iduser, idautoe,typematiere1);
        /*System.err.println("scienceeeeeeeeeeeeeeee"+iduser);
        System.err.println(TotaleScience);
        System.err.println("lettreeeeeeeeeeeeeeeee"+iduser);
        System.err.println(TotaleLettre);*/
        if (TotaleScience > TotaleLettre){
            return parcoursRepository.ParcoursEleveScience();
        }else {
            return parcoursRepository.ParcoursEleveLettre();
        }
    }

    //PARCOURS RECENTS DU PARCOURS ECOLE PROFESSIONNELLE D'UN ELEVE
    @GetMapping("AutorecenteEprof/{iduser}")
    public List<Parcours> Autorecenteeprof(@PathVariable("iduser") Long iduser){

        List<Autoevaluation> autoevaluation1 = autoevaluationRepository.findLatestByIdUser(iduser);
        Long idautoe = 0L;

        if (autoevaluation1.size() >1){
            for (Autoevaluation autoevaluation2: autoevaluation1){
                //System.err.println(autoevaluation2.getId());
                idautoe = autoevaluation2.getId();

            }
        }else {
            for (Autoevaluation autoevaluation2: autoevaluation1){
                idautoe = autoevaluation2.getId();
            }
        }
        /*System.err.println("AUTOEVALUATIONNNNNNNNNNNNNNNNNN"+iduser);
        System.err.println(idautoe);*/
        String typematiere="science";
        String typematiere1="lettre";
        Long TotaleScience = reponseRepository.TotaleScience(iduser, idautoe, typematiere);
        Long TotaleLettre = reponseRepository.TotaleLettre(iduser, idautoe,typematiere1);
        /*System.err.println("scienceeeeeeeeeeeeeeee"+iduser);
        System.err.println(TotaleScience);
        System.err.println("lettreeeeeeeeeeeeeeeee"+iduser);
        System.err.println(TotaleLettre);*/
        if (TotaleScience > TotaleLettre){
            return parcoursRepository.ParcoursEleveProfScience();
        }else {
            return parcoursRepository.ParcoursEleveProfLettre();
        }
    }


    //AFFICHER PARCOURS ELEVE LYCEE
    @GetMapping("/afficherParcoursUser/{Id}")
    public Object Afficher(@PathVariable Long Id){
        List<Autoevaluation> autoevaluation1 = autoevaluationRepository.findLatestByIdUser(Id);
        Long idauto = 0L;
        if (autoevaluation1.size() >=1){
            for (Autoevaluation autoevaluation2: autoevaluation1){
                System.err.println(autoevaluation2.getId());
                idauto = autoevaluation2.getId();

            }
        }else {
            for (Autoevaluation autoevaluation2: autoevaluation1){
                idauto = autoevaluation2.getId();
                System.err.println(autoevaluation2.getId());
            }
        }

        List<Autoevaluation> autouser = autoevaluationRepository.findAll();
        //System.err.println(autouser.size());


        String typematiere="science";
        String typematiere1="lettre";
        Long TotaleScience = reponseRepository.TotaleScience(Id, idauto, typematiere);
        Long TotaleLettre = reponseRepository.TotaleLettre(Id, idauto,typematiere1);
       /* System.err.println("scienceeeeeeeeeeeeeeee"+Id);
        System.err.println(TotaleScience);
        System.err.println("lettreeeeeeeeeeeeeeeee"+Id);
        System.err.println(TotaleLettre);*/



        //System.err.println(autoevaluation.getId());


        System.err.println("AUTOEVALUATIONNNNNNNNNNNNNNNNNN"+Id);
        System.err.println(idauto);
        if (TotaleScience > TotaleLettre){
            //parcoursRepository.ParcoursEleveProfScience();

            for (Parcours parcours: parcoursRepository.ParcoursEleveScience()){
                autoevaluationRepository.INSERTPARCOUAUTO(idauto,parcours.getId());
            }
            return parcoursRepository.ParcoursEleveScience();
        }else {
            //parcoursRepository.ParcoursEleveProfLettre();
            for (Parcours parcours: parcoursRepository.ParcoursEleveLettre()){
                autoevaluationRepository.INSERTPARCOUAUTO(idauto,parcours.getId());
            }
            return parcoursRepository.ParcoursEleveLettre();
        }
    }

    //AFFICHER PARCOURS ELEVE PROFESSIONNEL
    @GetMapping("/afficherParcoursEcoleProf/{Id}")
    public Object AfficherP(@PathVariable Long Id){
        List<Autoevaluation> autoevaluation1 = autoevaluationRepository.findLatestByIdUser(Id);
        Long idauto = 0L;

        if (autoevaluation1.size() >1){
            for (Autoevaluation autoevaluation2: autoevaluation1){
                //System.err.println(autoevaluation2.getId());
                idauto = autoevaluation2.getId();
            }
        }else {
            for (Autoevaluation autoevaluation2: autoevaluation1){
                idauto = autoevaluation2.getId();
            }
        }

        List<Autoevaluation> autouser = autoevaluationRepository.findAll();
        //System.err.println(autouser.size());


        String typematiere="science";
        String typematiere1="lettre";
        Long TotaleScience = reponseRepository.TotaleScience(Id, idauto, typematiere);
        Long TotaleLettre = reponseRepository.TotaleLettre(Id, idauto,typematiere1);
        /*System.err.println("scienceeeeeeeeeeeeeeee"+Id);
        System.err.println(TotaleScience);
        System.err.println("lettreeeeeeeeeeeeeeeee"+Id);
        System.err.println(TotaleLettre);*/

        //System.err.println(autoevaluation.getId());

       /* System.err.println("AUTOEVALUATIONNNNNNNNNNNNNNNNNN"+Id);
        System.err.println(idauto);*/
        if (TotaleScience > TotaleLettre){
            for (Parcours parcours: parcoursRepository.ParcoursEleveProfScience()){
                autoevaluationRepository.INSERTPARCOUAUTO(idauto,parcours.getId());
            }
            return parcoursRepository.ParcoursEleveProfScience();
        }else {
            for (Parcours parcours: parcoursRepository.ParcoursEleveProfLettre()){
                autoevaluationRepository.INSERTPARCOUAUTO(idauto,parcours.getId());
            }
            return parcoursRepository.ParcoursEleveProfLettre();
        }
    }

    //====================================FIN ELEVE=====================================================


    //=====================================DEBUT ETUDIANT===============================================
}
