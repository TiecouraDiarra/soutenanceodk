package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Parcours;
import SoutenanceBackend.soutenance.Models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    //LA REQUETTE PERMETTANT D'AFFICHER LES QUESTIONS DES ELEVES
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `question` WHERE question.id_typequestion=1;",nativeQuery = true)
    List<Question> Questioneleve();

    //LA REQUETTE PERMETTANT D'AFFICHER LES QUESTIONS DES PROFESSIONNELS(DOMAINES)
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `question` WHERE question.id_typequestion=2 AND question.id_type_matiere=19;",nativeQuery = true)
    List<Question> Questionprofessionnel();

    //LA REQUETTE PERMETTANT D'AFFICHER LES QUESTIONS DES PROFESSIONNELS(FILIERES)
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `question` WHERE question.id_typequestion=2 AND question.id_type_matiere=18;",nativeQuery = true)
    List<Question> QuestionprofessionnelFiliere();

    //LA REQUETTE PERMETTANT D'AFFICHER LES QUESTIONS DES ETUDIANTS QUI ONT FAIT LA TSECO
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `question` WHERE question.id_typequestion=3;",nativeQuery = true)
    List<Question> QuestionTSECO();

    //LA REQUETTE PERMETTANT D'AFFICHER LES QUESTIONS DES ETUDIANTS QUI ONT FAIT LA TSE
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `question` WHERE question.id_typequestion=4;",nativeQuery = true)
    List<Question> QuestionTSE();

    //LA REQUETTE PERMETTANT D'AFFICHER LES QUESTIONS DES ETUDIANTS QUI ONT FAIT LA TSEXP
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `question` WHERE question.id_typequestion=5;",nativeQuery = true)
    List<Question> QuestionTSEXP();

    //LA REQUETTE PERMETTANT D'AFFICHER LES QUESTIONS DES ETUDIANTS QUI ONT FAIT LA TLL
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `question` WHERE question.id_typequestion=6;",nativeQuery = true)
    List<Question> QuestionTLL();

    //LA REQUETTE PERMETTANT D'AFFICHER LES QUESTIONS DES ETUDIANTS QUI ONT FAIT LA TAL
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `question` WHERE question.id_typequestion=7;",nativeQuery = true)
    List<Question> QuestionTAL();

    //LA REQUETTE PERMETTANT D'AFFICHER LES QUESTIONS DES ETUDIANTS QUI ONT FAIT LA TSS
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `question` WHERE question.id_typequestion=8;",nativeQuery = true)
    List<Question> QuestionTSS();
}
