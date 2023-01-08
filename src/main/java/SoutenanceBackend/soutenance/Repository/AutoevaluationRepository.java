package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Autoevaluation;
import SoutenanceBackend.soutenance.Models.ERole;
import SoutenanceBackend.soutenance.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutoevaluationRepository extends JpaRepository<Autoevaluation, Long> {
    Autoevaluation findByNomautoevaluation(String noAutoevaluation);
}
