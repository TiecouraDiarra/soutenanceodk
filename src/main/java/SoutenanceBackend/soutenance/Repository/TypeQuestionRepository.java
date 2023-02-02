package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Autoevaluation;
import SoutenanceBackend.soutenance.Models.TypeQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeQuestionRepository extends JpaRepository<TypeQuestion, Long> {

    TypeQuestion findByNomtypequestion(String nomtypequestion);
}
