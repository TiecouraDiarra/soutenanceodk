package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Eleve;
import SoutenanceBackend.soutenance.Models.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    Etudiant findByNumero(String numero);

    /*Optional<Etudiant> findByEmail(String email);

    Optional<Etudiant> findByResetToken(String resetToken);*/

    Optional<Etudiant> findByNumeroOrEmail(String numero, String email);

    Boolean existsByNumero(String numero);

    Boolean existsByEmail(String email);

    @Query(value = "SELECT * FROM `etudiant` WHERE etudiant.id=:id",nativeQuery = true)
    Etudiant trouverEtudiantParId(Long id);
}
