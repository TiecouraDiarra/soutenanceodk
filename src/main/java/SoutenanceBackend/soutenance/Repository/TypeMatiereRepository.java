package SoutenanceBackend.soutenance.Repository;

import SoutenanceBackend.soutenance.Models.TypeMatiere;
import SoutenanceBackend.soutenance.Models.TypeQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeMatiereRepository extends JpaRepository<TypeMatiere, Long> {
    TypeMatiere findByNomtypematiere(String nomtypematiere);
}
