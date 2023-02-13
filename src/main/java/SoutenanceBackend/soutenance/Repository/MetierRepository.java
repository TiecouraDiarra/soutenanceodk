package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Metier;
import SoutenanceBackend.soutenance.Models.Parcours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface MetierRepository extends JpaRepository<Metier, Long> {
    @Modifying
    @Transactional
    @Query(value = "SELECT * FROM `metier`;",nativeQuery = true)
    List<Metier> AfficherTousLesMetiers();

    @Query(value = "SELECT COUNT(id) FROM `metier`;", nativeQuery = true)
    Long nombreMetierTotal();
}
