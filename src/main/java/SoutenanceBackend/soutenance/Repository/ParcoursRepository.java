package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Metier;
import SoutenanceBackend.soutenance.Models.Parcours;
import SoutenanceBackend.soutenance.Models.Reponse;
import SoutenanceBackend.soutenance.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ParcoursRepository extends JpaRepository<Parcours, Long> {
    /*@Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` WHERE niveauparcours.nomniveau=\"Lycee\" AND parcours.nomparcours=\"Biologie\";",nativeQuery = true)
    List<Parcours> ParcoursEleveLitterature();*/

    //PARCOURS SCIENCE AU LYCEE
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` WHERE parcours.type='science' AND niveauparcours.id=1;",nativeQuery = true)
    List<Parcours> ParcoursEleveScience();

    //PARCOURS ECOLE PROF SCIENCE
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` WHERE parcours.type='ProfScience' AND niveauparcours.id=2;",nativeQuery = true)
    List<Parcours> ParcoursEleveProfScience();

    //PARCOURS LETTRE AU LYCEE
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` WHERE parcours.type='lettre' AND niveauparcours.id=1;",nativeQuery = true)
    List<Parcours> ParcoursEleveLettre();

    //PARCOURS LETTRE == SCIENCE AU LYCEE
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` WHERE parcours.type='LettreScience' AND niveauparcours.id=1;",nativeQuery = true)
    List<Parcours> ParcoursEleveLettreScience();

    //PARCOURS LETTRE == SCIENCE AU PROF
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` WHERE parcours.type='ProfLettreScience' AND niveauparcours.id=2;",nativeQuery = true)
    List<Parcours> ParcoursEleveProfLettreScience();

    //PARCOURS LETTRE AU PROF
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` WHERE parcours.type='ProfLettre' AND niveauparcours.id=2;",nativeQuery = true)
    List<Parcours> ParcoursEleveProfLettre();

    //PARCOURS LETTRE AU LYCEE
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM parcours WHERE parcours.id_niveauparcours=1;",nativeQuery = true)
    List<Parcours> ParcoursLycee();

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM parcours WHERE parcours.id_niveauparcours=2;",nativeQuery = true)
    List<Parcours> ParcoursProfessionnel();

    //REQUETTE PERMETTANT D'AFFICHER LES FORMATIONS PROFESSIONNELLES
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM parcours WHERE parcours.id_niveauparcours=4;",nativeQuery = true)
    List<Parcours> FormationsProfessionnelles();

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM parcours WHERE parcours.id_niveauparcours=3;",nativeQuery = true)
    List<Parcours> ParcoursUniversite();

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM metier, parcours_metier \n" +
            "WHERE parcours_metier.parcours_id=:id",nativeQuery = true)
    List<Metier> metiersParParcours(Long id);


    //=================PARTIE ETUDIANT=======================

    //PARCOURS POUR LES TSE
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` WHERE parcours.type='TSEPRIMAIRE' AND niveauparcours.id=3;",nativeQuery = true)
    List<Parcours> ParcoursEtudiantTSE();

    //PARCOURS POUR LES TSEXP
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` WHERE parcours.type='TSEXPPRIMAIRE' AND niveauparcours.id=3;",nativeQuery = true)
    List<Parcours> ParcoursEtudiantTSEXP();

    //PARCOURS DOMAINE INFORMATIQUE
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` WHERE parcours.type='Informatique' AND niveauparcours.id=4;",nativeQuery = true)
    List<Parcours> ParcoursDomaineInformatique();

    //PARCOURS DOMAINE COMPTABILITE
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` WHERE parcours.type='Comptabilité' AND niveauparcours.id=4;",nativeQuery = true)
    List<Parcours> ParcoursDomaineComptabilite();

    //PARCOURS DOMAINE PAIE-RESSOURCES HUMAINES
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` WHERE parcours.type='Paie-Ressources-humaines' AND niveauparcours.id=4;",nativeQuery = true)
    List<Parcours> ParcoursDomainePaieRessourcesHumaines();

    //PARCOURS DOMAINE GESTION
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` WHERE parcours.type='Gestion' AND niveauparcours.id=4;",nativeQuery = true)
    List<Parcours> ParcoursDomaineGestion();

    //PARCOURS DOMAINE JURIDIQUE
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` WHERE parcours.type='Juridique' AND niveauparcours.id=4;",nativeQuery = true)
    List<Parcours> ParcoursDomaineJuridique();

    //PARCOURS POUR LES TLL LV1
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` \n" +
            "WHERE (parcours.type='LV1' OR parcours.type='LANGUE') \n" +
            "AND niveauparcours.id=3;",nativeQuery = true)
    List<Parcours> ParcoursEtudiantTLLLV1();

    //PARCOURS POUR LES TLL LV2
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` \n" +
            "WHERE (parcours.type='LV2' OR parcours.type='LANGUE') \n" +
            "AND niveauparcours.id=3;",nativeQuery = true)
    List<Parcours> ParcoursEtudiantTLLLV2();

    //PARCOURS POUR LES TLL LITTERATURE
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` \n" +
            "WHERE (parcours.type='LITTERATURE') \n" +
            "AND niveauparcours.id=3;",nativeQuery = true)
    List<Parcours> ParcoursEtudiantTLLLITTE();

    //PARCOURS POUR LES TSS PHILOSOPHIE
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` \n" +
            "WHERE (parcours.type='PHILOSOPHIE') \n" +
            "AND niveauparcours.id=3;",nativeQuery = true)
    List<Parcours> ParcoursEtudiantTSSPHILO();

    //PARCOURS POUR LES TSS HISTOIRE
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` \n" +
            "WHERE (parcours.type='HISTOIRE') \n" +
            "AND niveauparcours.id=3;",nativeQuery = true)
    List<Parcours> ParcoursEtudiantTSSHISTOIRE();

    //PARCOURS POUR LES TSECO ECONOMIE
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` \n" +
            "WHERE (parcours.type='ECONOMIE') \n" +
            "AND niveauparcours.id=3;",nativeQuery = true)
    List<Parcours> ParcoursEtudiantTSECOECONOMIE();

    //PARCOURS POUR LES TSECO COMPTABILITE
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` \n" +
            "WHERE (parcours.type='COMPTABILITE') \n" +
            "AND niveauparcours.id=3;",nativeQuery = true)
    List<Parcours> ParcoursEtudiantTSECOCOMPTABILITE();

    //PARCOURS POUR LES TSS GEOGRAPHIE
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` \n" +
            "WHERE (parcours.type='GEOGRAPHIE') \n" +
            "AND niveauparcours.id=3;",nativeQuery = true)
    List<Parcours> ParcoursEtudiantTSSGEOGRAPHIE();

    //PARCOURS POUR LES TSS SOCIOLOGIE
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` \n" +
            "WHERE (parcours.type='SOCIOLOGIE') \n" +
            "AND niveauparcours.id=3;",nativeQuery = true)
    List<Parcours> ParcoursEtudiantTSSSOCIOLOGIE();

    //LES IMAGES DES PARCOURS UNIVERSITE
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours` WHERE parcours.id_niveauparcours=3;",nativeQuery = true)
    List<Parcours> ImagesParcoursUniversite();

    //LES IMAGES DES FORMATIONS
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours` WHERE parcours.id_niveauparcours=4;",nativeQuery = true)
    List<Parcours> ImagesFormations();

    List<Parcours> findByType(String nomtype);

    @Query(value = "SELECT COUNT(id) FROM `parcours`;", nativeQuery = true)
    Long nombreParcoursTotal();
}
