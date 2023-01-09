package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Parcours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcoursRepository extends JpaRepository<Parcours, Long> {
}
