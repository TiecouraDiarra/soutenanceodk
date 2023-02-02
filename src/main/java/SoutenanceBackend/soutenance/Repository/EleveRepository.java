package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.Eleve;
import SoutenanceBackend.soutenance.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EleveRepository extends JpaRepository<Eleve, Long> {

    Eleve findByNumero(String numero);

    /*Optional<Eleve> findByEmail(String email);

    Optional<Eleve> findByResetToken(String resetToken);*/

    Optional<Eleve> findByNumeroOrEmail(String numero, String email);

    Boolean existsByNumero(String numero);

    Boolean existsByEmail(String email);
}
