package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Autoevaluation;
import SoutenanceBackend.soutenance.Models.ERole;
import SoutenanceBackend.soutenance.Models.Role;
import SoutenanceBackend.soutenance.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AutoevaluationRepository extends JpaRepository<Autoevaluation, Long> {
    //Autoevaluation findByNomautoevaluation(String noAutoevaluation);

    List<Autoevaluation> findByUtilisateur(User user);

    @Query(value = "SELECT users.id as 'user' ,matierequestion.matierequestion as 'question',reponse.reponse as 'reponse' \n" +
            "FROM users,question,reponse,autoevaluation,auto_reponse,matierequestion\n" +
            "WHERE users.id=autoevaluation.id_utilisateur\n" +
            "AND autoevaluation.id=auto_reponse.auto_id\n" +
            "AND auto_reponse.reponse_id=reponse.id\n" +
            "AND matierequestion.id=question.id_matierequestion\n" +
            "AND question.id=reponse.id_question \n" +
            "AND users.id=:id", nativeQuery = true)
    Iterable<Object[]> idAutoevaluation(Long id);

    @Query(value = "SELECT * FROM autoevaluation ae WHERE ae.id_utilisateur =:id ORDER BY ae.id",nativeQuery = true)
    List<Autoevaluation> findLatestByIdUser(@Param("id") Long id);

    // INSERTION DE PARCOURT ET AUTOEVALUTION DANS LA BASE DE DONNEE
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO `auto_parcours`(`auto_id`, `parcours_id`) values (:auto_id,:parcours_id);", nativeQuery = true)
    public void INSERTPARCOUAUTO(@Param("auto_id") Long auto_id, @Param("parcours_id") Long parcours_id);


    //LES QUATRES DERNIERES AUTOEVALUATIONS
    @Query(value = "SELECT ae.*\n" +
            "FROM autoevaluation ae\n" +
            "INNER JOIN (\n" +
            "   SELECT id_utilisateur, MAX(dateauto) AS last_autoevaluation\n" +
            "   FROM autoevaluation\n" +
            "   GROUP BY id_utilisateur\n" +
            "   ORDER BY last_autoevaluation DESC\n" +
            "   LIMIT 4\n" +
            ") last_ae\n" +
            "ON ae.id_utilisateur = last_ae.id_utilisateur AND ae.dateauto = last_ae.last_autoevaluation;",nativeQuery = true)
    List<Autoevaluation> QuatreAutoevaluationRecente();


}
