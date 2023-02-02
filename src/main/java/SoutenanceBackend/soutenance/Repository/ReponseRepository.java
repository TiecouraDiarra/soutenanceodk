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
}
