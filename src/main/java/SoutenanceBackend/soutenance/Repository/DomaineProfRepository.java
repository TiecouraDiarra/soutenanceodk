package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.DomaineProf;
import SoutenanceBackend.soutenance.Models.SerieLycee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DomaineProfRepository extends JpaRepository<DomaineProf, Long> {
    DomaineProf findByNomdomaine(String nomdomaine);
}
