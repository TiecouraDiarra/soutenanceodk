package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Etudiant;
import SoutenanceBackend.soutenance.Models.professionnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessionnelRepository extends JpaRepository<professionnel, Long> {
    professionnel findByNumero(String numero);

    /*Optional<professionnel> findByEmail(String email);

    Optional<professionnel> findByResetToken(String resetToken);*/

    Optional<professionnel> findByNumeroOrEmail(String numero, String email);

    Boolean existsByNumero(String numero);

    Boolean existsByEmail(String email);
}
