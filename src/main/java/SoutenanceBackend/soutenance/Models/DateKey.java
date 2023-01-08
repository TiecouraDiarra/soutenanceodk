package SoutenanceBackend.soutenance.Models;

import javax.persistence.Column;
import java.io.Serializable;

public class DateKey implements Serializable {
    @Column(name = "utilisateur_id")
    Long utilisateurId;

    @Column(name = "parcours_id")
    Long parcoursId;
}
