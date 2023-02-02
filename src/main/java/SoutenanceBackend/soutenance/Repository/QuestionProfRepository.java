package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.QuestionProfessionnel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionProfRepository extends JpaRepository<QuestionProfessionnel, Long> {
}
