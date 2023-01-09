package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
