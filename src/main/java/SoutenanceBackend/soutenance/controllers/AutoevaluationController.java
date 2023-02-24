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

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8100"}, maxAge = 3600, allowCredentials="true")
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
    private EtudiantRepository etudiantRepository;

    @Autowired
    private ProfessionnelRepository professionnelRepository;



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

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/afficher")
    public List<Autoevaluation> Afficher(){
        return autoevaluationService.Afficher();
    }

    //RECUPERER AUTOEVALUATION PAR ID
    @GetMapping("/RecupererAutoParId/{id_auto}")
    public Optional<Autoevaluation> autoevaluation(@PathVariable("id_auto") Long id_auto){
        return autoevaluationRepository.findById(id_auto);
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping({"/modifier"})
    public String ModierUser(@RequestBody Autoevaluation autoevaluation){
        autoevaluationService.Modifier(autoevaluation);
        return "Modification reussie avec succès";
    }

    //@PreAuthorize("hasRole('ADMIN')")
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
        }else if (TotaleScience == TotaleLettre){
            return parcoursRepository.ParcoursEleveLettreScience();
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
        }else if (TotaleScience == TotaleLettre){
            return parcoursRepository.ParcoursEleveProfLettreScience();
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


        Long TotaleScience = reponseRepository.TotaleScience(Id, idauto, "science");
        Long TotaleLettre = reponseRepository.TotaleLettre(Id, idauto,"lettre");
       /* System.err.println("scienceeeeeeeeeeeeeeee"+Id);
        System.err.println(TotaleScience);
        System.err.println("lettreeeeeeeeeeeeeeeee"+Id);
        System.err.println(TotaleLettre);*/



        //System.err.println(autoevaluation.getId());


        System.err.println("AUTOEVALUATIONNNNNNNNNNNNNNNNNN"+Id);
        System.err.println(idauto);
        if (TotaleScience > TotaleLettre){
            for (Parcours parcours: parcoursRepository.ParcoursEleveScience()){
                autoevaluationRepository.INSERTPARCOUAUTO(idauto,parcours.getId());
            }
            return parcoursRepository.ParcoursEleveScience();
        }else if (TotaleScience == TotaleLettre){
            for (Parcours parcours: parcoursRepository.ParcoursEleveLettreScience()){
                autoevaluationRepository.INSERTPARCOUAUTO(idauto,parcours.getId());
            }
            return parcoursRepository.ParcoursEleveLettreScience();
        }else {
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
        }else if (TotaleScience == TotaleLettre){
            for (Parcours parcours: parcoursRepository.ParcoursEleveProfLettreScience()){
                autoevaluationRepository.INSERTPARCOUAUTO(idauto,parcours.getId());
            }
            return parcoursRepository.ParcoursEleveProfLettreScience();
        }else {
            for (Parcours parcours: parcoursRepository.ParcoursEleveProfLettre()){
                autoevaluationRepository.INSERTPARCOUAUTO(idauto,parcours.getId());
            }
            return parcoursRepository.ParcoursEleveProfLettre();
        }
    }

    //====================================FIN ELEVE=====================================================


    //=====================================DEBUT ETUDIANT===============================================
    //AFFICHER PARCOURS ETUDIANT
    @GetMapping("/ResultatAutoEtudiant/{Id}")
    public Object ResultatAutoEtudiant(@PathVariable Long Id) {
        List<Autoevaluation> autoevaluation1 = autoevaluationRepository.findLatestByIdUser(Id);
        Long idauto = 0L;
        if (autoevaluation1.size() >= 1) {
            for (Autoevaluation autoevaluation2 : autoevaluation1) {
                System.err.println(autoevaluation2.getId());
                idauto = autoevaluation2.getId();

            }
        } else {
            for (Autoevaluation autoevaluation2 : autoevaluation1) {
                idauto = autoevaluation2.getId();
                System.err.println(autoevaluation2.getId());
            }
        }

        List<Autoevaluation> autouser = autoevaluationRepository.findAll();


        //VARIABLE QUI VA PRENDRE LE TYPE DE MATIERE
        String typematiere = "EtudiantTSE";


        //RECUPERATION DE L'ID DE L'ETUDIANT POUR FAIRE LA VERIFICATION
        Etudiant etudiant = etudiantRepository.findById(Id).get();

        //LA CONDITION PERMETTANT DE VERIFIER LA SERIE D'UN ETUDIANT TSE
        if (etudiant.getSerieLycee().getNomserie().equals("TSE")) {
            //TOTAL NOTE TSE
            Long TotalNoteEtudiantTSE = reponseRepository.NoteTSE(Id, idauto, typematiere);

            if (TotalNoteEtudiantTSE < 39) {
                for (Parcours parcours : parcoursRepository.ParcoursEtudiantTSEXP()) {
                    autoevaluationRepository.INSERTPARCOUAUTO(idauto, parcours.getId());
                }
                return parcoursRepository.ParcoursEtudiantTSEXP();

            } else {
                for (Parcours parcours : parcoursRepository.ParcoursEtudiantTSE()) {
                    autoevaluationRepository.INSERTPARCOUAUTO(idauto, parcours.getId());
                }
                return parcoursRepository.ParcoursEtudiantTSE();
            }
        }
        //LA CONDITION PERMETTANT DE VERIFIER LA SERIE D'UN ETUDIANT TSECO
        else if (etudiant.getSerieLycee().getNomserie().equals("TSECO")) {
            //TOTAL NOTE TSECO ECONOMIE
            Long TotalNoteEtudiantTSECOECONOMIE = reponseRepository.NoteTSECOECONOMIE(Id, idauto, "TSECOECONOMIE");

            //TOTAL NOTE TSECO GEOGRAPHIE
            Long TotalNoteEtudiantTSECOGEOGRAPHIE = reponseRepository.NoteTSECOGEOGRAPHIE(Id, idauto, "TSECOGEOGRAPHIE");

            //TOTAL NOTE TSECO COMPTABILITE
            Long TotalNoteEtudiantTSECOCOMPTABILITE = reponseRepository.NoteTSECOCOMPTABILITE(Id, idauto, "TSECOCOMPTABILITE");
            if (TotalNoteEtudiantTSECOECONOMIE >= TotalNoteEtudiantTSECOGEOGRAPHIE && TotalNoteEtudiantTSECOECONOMIE >= TotalNoteEtudiantTSECOCOMPTABILITE) {
                for (Parcours parcours : parcoursRepository.ParcoursEtudiantTSECOECONOMIE()) {
                    autoevaluationRepository.INSERTPARCOUAUTO(idauto, parcours.getId());
                }
                return parcoursRepository.ParcoursEtudiantTSECOECONOMIE();
            } else if (TotalNoteEtudiantTSECOGEOGRAPHIE >= TotalNoteEtudiantTSECOECONOMIE && TotalNoteEtudiantTSECOGEOGRAPHIE >= TotalNoteEtudiantTSECOCOMPTABILITE) {
                for (Parcours parcours : parcoursRepository.ParcoursEtudiantTSSGEOGRAPHIE()) {
                    autoevaluationRepository.INSERTPARCOUAUTO(idauto, parcours.getId());
                }
                return parcoursRepository.ParcoursEtudiantTSSGEOGRAPHIE();
            } else {
                for (Parcours parcours : parcoursRepository.ParcoursEtudiantTSECOCOMPTABILITE()) {
                    autoevaluationRepository.INSERTPARCOUAUTO(idauto, parcours.getId());
                }
                return parcoursRepository.ParcoursEtudiantTSECOCOMPTABILITE();
            }
        }
        //LA CONDITION PERMETTANT DE VERIFIER LA SERIE D'UN ETUDIANT TSEXP
        else if (etudiant.getSerieLycee().getNomserie().equals("TSEXP")) {

            //TOTAL NOTE TSEXP
            Long TotalNoteEtudiantTSEXP = reponseRepository.NoteTSEXP(Id, idauto, "EtudiantTSEXP");

            if (TotalNoteEtudiantTSEXP < 36) {
                for (Parcours parcours : parcoursRepository.ParcoursEtudiantTSE()) {
                    autoevaluationRepository.INSERTPARCOUAUTO(idauto, parcours.getId());
                }
                return parcoursRepository.ParcoursEtudiantTSE();
            } else {
                for (Parcours parcours : parcoursRepository.ParcoursEtudiantTSEXP()) {
                    autoevaluationRepository.INSERTPARCOUAUTO(idauto, parcours.getId());
                }
                return parcoursRepository.ParcoursEtudiantTSEXP();
            }
        }
        //LA CONDITION PERMETTANT DE VERIFIER LA SERIE D'UN ETUDIANT TLL
        else if (etudiant.getSerieLycee().getNomserie().equals("TLL")) {
            //TOTAL NOTE TLL LV1
            Long TotalNoteEtudiantTLLLV1 = reponseRepository.NoteLV1(Id, idauto, "LV1TLL");

            //TOTAL NOTE TLL LV2
            Long TotalNoteEtudiantTLLLV2 = reponseRepository.NoteLV2(Id, idauto, "LV2TLL");

            //TOTAL NOTE TLL LITTERATURE
            Long TotalNoteEtudiantTLLLITTE = reponseRepository.NoteLITTERATURE(Id, idauto, "LITTETLL");


            if (TotalNoteEtudiantTLLLV2 >= TotalNoteEtudiantTLLLV1 && TotalNoteEtudiantTLLLV2 >= TotalNoteEtudiantTLLLITTE) {
                for (Parcours parcours : parcoursRepository.ParcoursEtudiantTLLLV2()) {
                    autoevaluationRepository.INSERTPARCOUAUTO(idauto, parcours.getId());
                }
                return parcoursRepository.ParcoursEtudiantTLLLV2();
            } else if (TotalNoteEtudiantTLLLV1 >= TotalNoteEtudiantTLLLV2 && TotalNoteEtudiantTLLLV1 >= TotalNoteEtudiantTLLLITTE) {
                for (Parcours parcours : parcoursRepository.ParcoursEtudiantTLLLV1()) {
                    autoevaluationRepository.INSERTPARCOUAUTO(idauto, parcours.getId());
                }
                return parcoursRepository.ParcoursEtudiantTLLLV1();
            } else {
                for (Parcours parcours : parcoursRepository.ParcoursEtudiantTLLLITTE()) {
                    autoevaluationRepository.INSERTPARCOUAUTO(idauto, parcours.getId());
                }
                return parcoursRepository.ParcoursEtudiantTLLLITTE();
            }
        }

        //LA CONDITION PERMETTANT DE VERIFIER LA SERIE D'UN ETUDIANT TAL
        else if (etudiant.getSerieLycee().getNomserie().equals("TAL")) {
            return null;
        }

        //LA CONDITION PERMETTANT DE VERIFIER LA SERIE D'UN ETUDIANT TSS
        else if (etudiant.getSerieLycee().getNomserie().equals("TSS")) {
            //TOTAL NOTE TSS PHILO
            Long TotalNoteEtudiantTSSPHILO = reponseRepository.NoteLV1(Id, idauto, "PHILOTSS");

            //TOTAL NOTE TSS HISTOIRE
            Long TotalNoteEtudiantTSSHISTOIRE = reponseRepository.NoteLV2(Id, idauto, "HISTTSS");

            //TOTAL NOTE TSS GEOGRAPHIE
            Long TotalNoteEtudiantTSSGEO = reponseRepository.NoteLITTERATURE(Id, idauto, "GEOTSS");

            //TOTAL NOTE TSS SOCIOLOGIE
            Long TotalNoteEtudiantTSSSOCIOLOGIE = reponseRepository.NoteLITTERATURE(Id, idauto, "SOCIOTSS");

            if (TotalNoteEtudiantTSSPHILO >= TotalNoteEtudiantTSSHISTOIRE && TotalNoteEtudiantTSSPHILO >= TotalNoteEtudiantTSSGEO && TotalNoteEtudiantTSSPHILO >= TotalNoteEtudiantTSSSOCIOLOGIE) {
                for (Parcours parcours : parcoursRepository.ParcoursEtudiantTSSPHILO()) {
                    autoevaluationRepository.INSERTPARCOUAUTO(idauto, parcours.getId());
                }
                return parcoursRepository.ParcoursEtudiantTSSPHILO();
            } else if (TotalNoteEtudiantTSSHISTOIRE >= TotalNoteEtudiantTSSPHILO && TotalNoteEtudiantTSSHISTOIRE >= TotalNoteEtudiantTSSGEO && TotalNoteEtudiantTSSHISTOIRE >= TotalNoteEtudiantTSSSOCIOLOGIE) {
                for (Parcours parcours : parcoursRepository.ParcoursEtudiantTSSHISTOIRE()) {
                    autoevaluationRepository.INSERTPARCOUAUTO(idauto, parcours.getId());
                }
                return parcoursRepository.ParcoursEtudiantTSSHISTOIRE();
            } else if (TotalNoteEtudiantTSSGEO >= TotalNoteEtudiantTSSPHILO && TotalNoteEtudiantTSSGEO >= TotalNoteEtudiantTSSHISTOIRE && TotalNoteEtudiantTSSGEO >= TotalNoteEtudiantTSSSOCIOLOGIE) {
                for (Parcours parcours : parcoursRepository.ParcoursEtudiantTSSGEOGRAPHIE()) {
                    autoevaluationRepository.INSERTPARCOUAUTO(idauto, parcours.getId());
                }
                return parcoursRepository.ParcoursEtudiantTSSGEOGRAPHIE();
            }else {
                for (Parcours parcours : parcoursRepository.ParcoursEtudiantTSSSOCIOLOGIE()) {
                    autoevaluationRepository.INSERTPARCOUAUTO(idauto, parcours.getId());
                }
                return parcoursRepository.ParcoursEtudiantTSSSOCIOLOGIE();
            }
        }else {
            return "Pas de Parcours";
        }
    }


    //PARCOURS RECENTS DU PARCOURS D'UN ETUDIANT
    @GetMapping("Autorecenteetudiant/{iduser}")
    public List<Parcours> Autorecenteetudiant(@PathVariable("iduser") Long iduser){

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

        System.err.println("AUTOEVALUATIONNNNNNNNNNNNNNNNNN"+iduser);
        System.err.println(idautoe);

        Etudiant etudiant = etudiantRepository.findById(iduser).get();

        //LA CONDITION PERMETTANT DE VERIFIER LA SERIE D'UN ETUDIANT
        if (etudiant.getSerieLycee().getNomserie().equals("TSE")){
            //TOTAL NOTE TSE
            Long TotalNoteEtudiantTSE = reponseRepository.NoteTSE(iduser, idautoe, "EtudiantTSE");

            if (TotalNoteEtudiantTSE < 39){
                return parcoursRepository.ParcoursEtudiantTSEXP();
            }else {
                return parcoursRepository.ParcoursEtudiantTSE();
            }
        } else if (etudiant.getSerieLycee().getNomserie().equals("TSECO")) {
            return null;
        }else if (etudiant.getSerieLycee().getNomserie().equals("TSEXP")) {
            //TOTAL NOTE TSEXP
            Long TotalNoteEtudiantTSEXP = reponseRepository.NoteTSEXP(iduser, idautoe, "EtudiantTSEXP");
            if (TotalNoteEtudiantTSEXP < 36){
                return parcoursRepository.ParcoursEtudiantTSE();
            }else {
                return parcoursRepository.ParcoursEtudiantTSEXP();
            }
        }else if (etudiant.getSerieLycee().getNomserie().equals("TLL")) {
            //TOTAL NOTE TLL LV1
            Long TotalNoteEtudiantTLLLV1 = reponseRepository.NoteLV1(iduser, idautoe, "LV1TLL");

            //TOTAL NOTE TLL LV2
            Long TotalNoteEtudiantTLLLV2 = reponseRepository.NoteLV2(iduser, idautoe, "LV2TLL");

            //TOTAL NOTE TLL LITTERATURE
            Long TotalNoteEtudiantTLLLITTE = reponseRepository.NoteLITTERATURE(iduser, idautoe, "LITTETLL");

            if (TotalNoteEtudiantTLLLV2 >= TotalNoteEtudiantTLLLV1 && TotalNoteEtudiantTLLLV2 >= TotalNoteEtudiantTLLLITTE){
                return parcoursRepository.ParcoursEtudiantTLLLV2();
            }else if (TotalNoteEtudiantTLLLV1 >= TotalNoteEtudiantTLLLV2 && TotalNoteEtudiantTLLLV1 >= TotalNoteEtudiantTLLLITTE){
                return parcoursRepository.ParcoursEtudiantTLLLV1();
            }else {
                return parcoursRepository.ParcoursEtudiantTLLLITTE();
            }
        }else if (etudiant.getSerieLycee().getNomserie().equals("TAL")) {
            return null;
        }else if (etudiant.getSerieLycee().getNomserie().equals("TSS")){
            //TOTAL NOTE TSS PHILO
            Long TotalNoteEtudiantTSSPHILO = reponseRepository.NoteLV1(iduser, idautoe, "PHILOTSS");

            //TOTAL NOTE TSS HISTOIRE
            Long TotalNoteEtudiantTSSHISTOIRE = reponseRepository.NoteLV2(iduser, idautoe, "HISTTSS");

            //TOTAL NOTE TSS GEOGRAPHIE
            Long TotalNoteEtudiantTSSGEO = reponseRepository.NoteLITTERATURE(iduser, idautoe, "GEOTSS");

            //TOTAL NOTE TSS SOCIOLOGIE
            Long TotalNoteEtudiantTSSSOCIOLOGIE = reponseRepository.NoteLITTERATURE(iduser, idautoe, "SOCIOTSS");
            if (TotalNoteEtudiantTSSPHILO >= TotalNoteEtudiantTSSHISTOIRE && TotalNoteEtudiantTSSPHILO >= TotalNoteEtudiantTSSGEO && TotalNoteEtudiantTSSPHILO >= TotalNoteEtudiantTSSSOCIOLOGIE) {
                return parcoursRepository.ParcoursEtudiantTSSPHILO();
            } else if (TotalNoteEtudiantTSSHISTOIRE >= TotalNoteEtudiantTSSPHILO && TotalNoteEtudiantTSSHISTOIRE >= TotalNoteEtudiantTSSGEO && TotalNoteEtudiantTSSHISTOIRE >= TotalNoteEtudiantTSSSOCIOLOGIE) {
                return parcoursRepository.ParcoursEtudiantTSSHISTOIRE();
            } else if (TotalNoteEtudiantTSSGEO >= TotalNoteEtudiantTSSPHILO && TotalNoteEtudiantTSSGEO >= TotalNoteEtudiantTSSHISTOIRE && TotalNoteEtudiantTSSGEO >= TotalNoteEtudiantTSSSOCIOLOGIE) {
                return parcoursRepository.ParcoursEtudiantTSSGEOGRAPHIE();
            }else {
                return parcoursRepository.ParcoursEtudiantTSSSOCIOLOGIE();
            }
        }else {
            return null;
        }

    }
    //==============================FIN ETUDIANT=====================================================

    //==================================DEBUT PROFESSIONNEL=====================================================
    //AFFICHER PARCOURS PROFESSIONNEL
    @GetMapping("/ResultatAutoProfessionnel/{Id}")
    public Object ResultatAutoProfessionnel(@PathVariable Long Id) {
        List<Autoevaluation> autoevaluation1 = autoevaluationRepository.findLatestByIdUser(Id);
        Long idauto = 0L;
        if (autoevaluation1.size() >= 1) {
            for (Autoevaluation autoevaluation2 : autoevaluation1) {
                System.err.println(autoevaluation2.getId());
                idauto = autoevaluation2.getId();

            }
        } else {
            for (Autoevaluation autoevaluation2 : autoevaluation1) {
                idauto = autoevaluation2.getId();
                System.err.println(autoevaluation2.getId());
            }
        }

        //RECUPERER LA REPONSE PROFESSIONNELLE
        String RecupererDomaineProf = reponseRepository.ReponseDomaineProfessionnel(Id, idauto, "Prof2");
        System.out.println(RecupererDomaineProf);
        //LA CONDITION PERMETTANT DE VERIFIER LE DOMAINE D'UN PROFESSIONNEL
        if (RecupererDomaineProf.equals("Informatique")) {
            for (Parcours parcours: parcoursRepository.ParcoursDomaineInformatique()){
                autoevaluationRepository.INSERTPARCOUAUTO(idauto,parcours.getId());
            }
            return parcoursRepository.ParcoursDomaineInformatique();
        }
        //LA CONDITION PERMETTANT DE VERIFIER LE DOMAINE D'UN PROFESSIONNEL
        else if (RecupererDomaineProf.equals("Comptabilité")) {
            for (Parcours parcours: parcoursRepository.ParcoursDomaineComptabilite()){
                autoevaluationRepository.INSERTPARCOUAUTO(idauto,parcours.getId());
            }
            return parcoursRepository.ParcoursDomaineComptabilite();
        }
        //LA CONDITION PERMETTANT DE VERIFIER LE DOMAINE D'UN PROFESSIONNEL
        else if (RecupererDomaineProf.equals("Banque / Assurance")) {
            for (Parcours parcours: parcoursRepository.ParcoursEtudiantTSEXP()){
                autoevaluationRepository.INSERTPARCOUAUTO(idauto,parcours.getId());
            }
            return parcoursRepository.ParcoursEtudiantTSEXP();
        }
        //LA CONDITION PERMETTANT DE VERIFIER LE DOMAINE D'UN PROFESSIONNEL
        else if (RecupererDomaineProf.equals("Électronique / Électricité")) {
            for (Parcours parcours: parcoursRepository.ParcoursEleveScience()){
                autoevaluationRepository.INSERTPARCOUAUTO(idauto,parcours.getId());
            }
            return parcoursRepository.ParcoursEtudiantTSEXP();
        }
        //LA CONDITION PERMETTANT DE VERIFIER LE DOMAINE D'UN PROFESSIONNEL
        else if (RecupererDomaineProf.equals("Paie-Ressources-Humaines")) {
            for (Parcours parcours: parcoursRepository.ParcoursDomainePaieRessourcesHumaines()){
                autoevaluationRepository.INSERTPARCOUAUTO(idauto,parcours.getId());
            }
            return parcoursRepository.ParcoursDomainePaieRessourcesHumaines();
        }
        //LA CONDITION PERMETTANT DE VERIFIER LE DOMAINE D'UN PROFESSIONNEL
        else if (RecupererDomaineProf.equals("Gestion")) {
            for (Parcours parcours: parcoursRepository.ParcoursDomaineGestion()){
                autoevaluationRepository.INSERTPARCOUAUTO(idauto,parcours.getId());
            }
            return parcoursRepository.ParcoursDomaineGestion();
        }
        //LA CONDITION PERMETTANT DE VERIFIER LE DOMAINE D'UN PROFESSIONNEL
        else if (RecupererDomaineProf.equals("Juridique")) {
            for (Parcours parcours: parcoursRepository.ParcoursDomaineJuridique()){
                autoevaluationRepository.INSERTPARCOUAUTO(idauto,parcours.getId());
            }
            return parcoursRepository.ParcoursDomaineJuridique();
        }
        else {
            return "Pas de Formation";
        }
    }

    //PARCOURS RECENTS D'UN PROFESSIONNEL
    @GetMapping("AutorecenteProfessionnel/{iduser}")
    public List<Parcours> Autorecenteprofessionnel(@PathVariable("iduser") Long iduser){

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

        System.err.println("AUTOEVALUATIONNNNNNNNNNNNNNNNNN"+iduser);
        System.err.println(idautoe);

        //RECUPERER LA REPONSE PROFESSIONNELLE
        String RecupererDomaineProf = reponseRepository.ReponseDomaineProfessionnel(iduser, idautoe, "Prof2");
        System.out.println(RecupererDomaineProf);
        //LA CONDITION PERMETTANT DE VERIFIER LE DOMAINE D'UN PROFESSIONNEL
        if (RecupererDomaineProf.equals("Informatique")) {
            return parcoursRepository.ParcoursDomaineInformatique();
        }
        //LA CONDITION PERMETTANT DE VERIFIER LE DOMAINE D'UN PROFESSIONNEL
        else if (RecupererDomaineProf.equals("Comptabilité")) {
            return parcoursRepository.ParcoursDomaineComptabilite();
        }
        //LA CONDITION PERMETTANT DE VERIFIER LE DOMAINE D'UN PROFESSIONNEL
        else if (RecupererDomaineProf.equals("Banque / Assurance")) {
            return parcoursRepository.ParcoursEtudiantTSEXP();
        }
        //LA CONDITION PERMETTANT DE VERIFIER LE DOMAINE D'UN PROFESSIONNEL
        else if (RecupererDomaineProf.equals("Électronique / Électricité")) {
            return parcoursRepository.ParcoursEtudiantTSEXP();
        }
        //LA CONDITION PERMETTANT DE VERIFIER LE DOMAINE D'UN PROFESSIONNEL
        else if (RecupererDomaineProf.equals("Paie-Ressources-Humaines")) {
            return parcoursRepository.ParcoursDomainePaieRessourcesHumaines();
        }
        //LA CONDITION PERMETTANT DE VERIFIER LE DOMAINE D'UN PROFESSIONNEL
        else if (RecupererDomaineProf.equals("Gestion")) {
            return parcoursRepository.ParcoursDomaineGestion();
        }
        //LA CONDITION PERMETTANT DE VERIFIER LE DOMAINE D'UN PROFESSIONNEL
        else if (RecupererDomaineProf.equals("Juridique")) {
            return parcoursRepository.ParcoursDomaineJuridique();
        }
        else {
            return null;
        }
    }

    //AFFICHER FORMATION PAR DOMAINE
    @GetMapping("/AfficherFormationParDomaine/{nomtype}")
    public Object AfficherFormationParDomaine(@PathVariable String nomtype) {

        //professionnel professionnel = professionnelRepository.findById(Id).get();

        List<Parcours> ppp = parcoursRepository.findByType(nomtype);
        return ppp;

    }
//==============================FIN PROFESSIONNEL=====================================================

    @GetMapping("AutorecenteQuatreUser")
    public List<Autoevaluation> AutorecenteUser(){
        return autoevaluationRepository.QuatreAutoevaluationRecente();
    }
}
