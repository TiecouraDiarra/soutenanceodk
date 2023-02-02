package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.SerieLycee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieLyceeRepository extends JpaRepository<SerieLycee, Long> {
    SerieLycee findByNomserie(String nomserie);
}
