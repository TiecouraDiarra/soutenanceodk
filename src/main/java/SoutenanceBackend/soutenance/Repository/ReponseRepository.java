package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Reponse;
import SoutenanceBackend.soutenance.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface ReponseRepository extends JpaRepository<Reponse, Long> {
    Reponse findByReponse(String reponse);
    //List<Reponse> findByUtilisateur(User user);

    //REQUETTE ERMETTANT DE CALCULER LES NOTES DES SCIENCES D'UN ELEVE
    @Query(value = "SELECT SUM(reponse) FROM " +
            "users,Question,Reponse,Autoevaluation,auto_reponse,Typematiere " +
            "WHERE users.id=autoevaluation.id_utilisateur " +
            "AND autoevaluation.id=auto_reponse.auto_id " +
            "AND auto_reponse.reponse_id=reponse.id " +
            "AND question.id_type_matiere=typematiere.id " +
            "AND question.id=reponse.id_question " +
            "AND users.id=:id " +
            "AND autoevaluation.id =:idauto " +
            "AND typematiere.nomtypematiere =:nomtypematiere", nativeQuery = true)
    Long TotaleScience(@Param("id") Long id, Long idauto, @Param("nomtypematiere") String typematiere);

    //REQUETTE ERMETTANT DE CALCULER LES NOTES DES LETTRES D'UN ELEVE
    @Query(value = "SELECT SUM(reponse) FROM " +
            "users,question,reponse,autoevaluation,auto_reponse,typematiere " +
            "WHERE users.id=autoevaluation.id_utilisateur " +
            "AND autoevaluation.id=auto_reponse.auto_id " +
            "AND auto_reponse.reponse_id=reponse.id " +
            "AND question.id_type_matiere=typematiere.id " +
            "AND question.id=reponse.id_question " +
            "AND users.id=:id " +
            "AND autoevaluation.id =:idauto " +
            "AND typematiere.nomtypematiere =:typematiere", nativeQuery = true)
    Long TotaleLettre(Long id, Long idauto, String typematiere);

    //REQUETTE ERMETTANT DE CALCULER LES NOTES DES ELEVES EN TSE
    @Query(value = "SELECT SUM(reponse) FROM " +
            "users,question,reponse,autoevaluation,auto_reponse,typematiere " +
            "WHERE users.id=autoevaluation.id_utilisateur " +
            "AND autoevaluation.id=auto_reponse.auto_id " +
            "AND auto_reponse.reponse_id=reponse.id " +
            "AND question.id_type_matiere=typematiere.id " +
            "AND question.id=reponse.id_question " +
            "AND users.id=:id " +
            "AND autoevaluation.id =:idauto " +
            "AND typematiere.nomtypematiere =:typematiere", nativeQuery = true)
    Long NoteTSE(Long id, Long idauto, String typematiere);

    //REQUETTE ERMETTANT DE CALCULER LES NOTES DES ELEVES EN TSEXP
    @Query(value = "SELECT SUM(reponse) FROM " +
            "users,question,reponse,autoevaluation,auto_reponse,typematiere " +
            "WHERE users.id=autoevaluation.id_utilisateur " +
            "AND autoevaluation.id=auto_reponse.auto_id " +
            "AND auto_reponse.reponse_id=reponse.id " +
            "AND question.id_type_matiere=typematiere.id " +
            "AND question.id=reponse.id_question " +
            "AND users.id=:id " +
            "AND autoevaluation.id =:idauto " +
            "AND typematiere.nomtypematiere =:typematiere", nativeQuery = true)
    Long NoteTSEXP(Long id, Long idauto, String typematiere);

    //REQUETTE ERMETTANT DE CALCULER LES NOTES DES ELEVES EN TLL LV1
    @Query(value = "SELECT SUM(reponse) FROM " +
            "users,question,reponse,autoevaluation,auto_reponse,typematiere " +
            "WHERE users.id=autoevaluation.id_utilisateur " +
            "AND autoevaluation.id=auto_reponse.auto_id " +
            "AND auto_reponse.reponse_id=reponse.id " +
            "AND question.id_type_matiere=typematiere.id " +
            "AND question.id=reponse.id_question " +
            "AND users.id=:id " +
            "AND autoevaluation.id =:idauto " +
            "AND typematiere.nomtypematiere =:typematiere", nativeQuery = true)
    Long NoteLV1(Long id, Long idauto, String typematiere);

    //REQUETTE ERMETTANT DE CALCULER LES NOTES DES ELEVES EN TLL LV2
    @Query(value = "SELECT SUM(reponse) FROM " +
            "users,question,reponse,autoevaluation,auto_reponse,typematiere " +
            "WHERE users.id=autoevaluation.id_utilisateur " +
            "AND autoevaluation.id=auto_reponse.auto_id " +
            "AND auto_reponse.reponse_id=reponse.id " +
            "AND question.id_type_matiere=typematiere.id " +
            "AND question.id=reponse.id_question " +
            "AND users.id=:id " +
            "AND autoevaluation.id =:idauto " +
            "AND typematiere.nomtypematiere =:typematiere", nativeQuery = true)
    Long NoteLV2(Long id, Long idauto, String typematiere);

    //REQUETTE PERMETTANT DE CALCULER LES NOTES DES ELEVES EN TLL LITTERATURE
    @Query(value = "SELECT SUM(reponse) FROM " +
            "users,question,reponse,autoevaluation,auto_reponse,typematiere " +
            "WHERE users.id=autoevaluation.id_utilisateur " +
            "AND autoevaluation.id=auto_reponse.auto_id " +
            "AND auto_reponse.reponse_id=reponse.id " +
            "AND question.id_type_matiere=typematiere.id " +
            "AND question.id=reponse.id_question " +
            "AND users.id=:id " +
            "AND autoevaluation.id =:idauto " +
            "AND typematiere.nomtypematiere =:typematiere", nativeQuery = true)
    Long NoteLITTERATURE(Long id, Long idauto, String typematiere);

    //REQUETTE ERMETTANT DE CALCULER LES NOTES DES ELEVES EN TSS PHILOSOPHIE
    @Query(value = "SELECT SUM(reponse) FROM " +
            "users,question,reponse,autoevaluation,auto_reponse,typematiere " +
            "WHERE users.id=autoevaluation.id_utilisateur " +
            "AND autoevaluation.id=auto_reponse.auto_id " +
            "AND auto_reponse.reponse_id=reponse.id " +
            "AND question.id_type_matiere=typematiere.id " +
            "AND question.id=reponse.id_question " +
            "AND users.id=:id " +
            "AND autoevaluation.id =:idauto " +
            "AND typematiere.nomtypematiere =:typematiere", nativeQuery = true)
    Long NoteTSSPHILO(Long id, Long idauto, String typematiere);

    //REQUETTE ERMETTANT DE CALCULER LES NOTES DES ELEVES EN TSS HISTOIRE
    @Query(value = "SELECT SUM(reponse) FROM " +
            "users,question,reponse,autoevaluation,auto_reponse,typematiere " +
            "WHERE users.id=autoevaluation.id_utilisateur " +
            "AND autoevaluation.id=auto_reponse.auto_id " +
            "AND auto_reponse.reponse_id=reponse.id " +
            "AND question.id_type_matiere=typematiere.id " +
            "AND question.id=reponse.id_question " +
            "AND users.id=:id " +
            "AND autoevaluation.id =:idauto " +
            "AND typematiere.nomtypematiere =:typematiere", nativeQuery = true)
    Long NoteTSSHISTOIRE(Long id, Long idauto, String typematiere);

    //REQUETTE ERMETTANT DE CALCULER LES NOTES DES ELEVES EN TSS GEOGRAPHIE
    @Query(value = "SELECT SUM(reponse) FROM " +
            "users,question,reponse,autoevaluation,auto_reponse,typematiere " +
            "WHERE users.id=autoevaluation.id_utilisateur " +
            "AND autoevaluation.id=auto_reponse.auto_id " +
            "AND auto_reponse.reponse_id=reponse.id " +
            "AND question.id_type_matiere=typematiere.id " +
            "AND question.id=reponse.id_question " +
            "AND users.id=:id " +
            "AND autoevaluation.id =:idauto " +
            "AND typematiere.nomtypematiere =:typematiere", nativeQuery = true)
    Long NoteTSSGEOGRAPHIE(Long id, Long idauto, String typematiere);

    //REQUETTE ERMETTANT DE CALCULER LES NOTES DES ELEVES EN TSS GEOGRAPHIE
    @Query(value = "SELECT SUM(reponse) FROM " +
            "users,question,reponse,autoevaluation,auto_reponse,typematiere " +
            "WHERE users.id=autoevaluation.id_utilisateur " +
            "AND autoevaluation.id=auto_reponse.auto_id " +
            "AND auto_reponse.reponse_id=reponse.id " +
            "AND question.id_type_matiere=typematiere.id " +
            "AND question.id=reponse.id_question " +
            "AND users.id=:id " +
            "AND autoevaluation.id =:idauto " +
            "AND typematiere.nomtypematiere =:typematiere", nativeQuery = true)
    Long NoteTSSSOCIOLOGIE(Long id, Long idauto, String typematiere);

    //REQUETTE PERMETTANT DE RECUPERER LA REPONSE DE PROFESSIONNEL
    @Query(value = "SELECT reponse FROM " +
            "users,question,reponse,autoevaluation,auto_reponse,typematiere " +
            "WHERE users.id=autoevaluation.id_utilisateur " +
            "AND autoevaluation.id=auto_reponse.auto_id " +
            "AND auto_reponse.reponse_id=reponse.id " +
            "AND question.id_type_matiere=typematiere.id " +
            "AND question.id=reponse.id_question " +
            "AND users.id=:id " +
            "AND autoevaluation.id =:idauto " +
            "AND typematiere.nomtypematiere =:typematiere", nativeQuery = true)
    String ReponseDomaineProfessionnel(Long id, Long idauto, String typematiere);
}
