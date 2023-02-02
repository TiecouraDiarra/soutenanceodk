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

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` WHERE parcours.filiere='science' AND niveauparcours.id=1;",nativeQuery = true)
    List<Parcours> ParcoursEleveScience();

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` WHERE parcours.filiere='ProfScience' AND niveauparcours.id=2;",nativeQuery = true)
    List<Parcours> ParcoursEleveProfScience();

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` WHERE parcours.filiere='lettre' AND niveauparcours.id=1;",nativeQuery = true)
    List<Parcours> ParcoursEleveLettre();

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `parcours`, `niveauparcours` WHERE parcours.filiere='ProfLettre' AND niveauparcours.id=2;",nativeQuery = true)
    List<Parcours> ParcoursEleveProfLettre();


    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM parcours WHERE parcours.id_niveauparcours=1;",nativeQuery = true)
    List<Parcours> ParcoursLycee();

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM parcours WHERE parcours.id_niveauparcours=2;",nativeQuery = true)
    List<Parcours> ParcoursProfessionnel();

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM parcours WHERE parcours.id_niveauparcours=3;",nativeQuery = true)
    List<Parcours> ParcoursUniversite();

    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM metier, parcours_metier \n" +
            "WHERE parcours_metier.parcours_id=:id",nativeQuery = true)
    List<Metier> metiersParParcours(Long id);
}
