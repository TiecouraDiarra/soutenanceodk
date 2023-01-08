package SoutenanceBackend.soutenance.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "dateevaluation")
@NoArgsConstructor
@AllArgsConstructor
public class Dateevaluation {
    @EmbeddedId
    DateKey id;

    @ManyToOne
    @MapsId("utilisateurId")
    @JoinColumn(name = "utilisateur_id")
    User utilisateur;

    @ManyToOne
    @MapsId("parcoursId")
    @JoinColumn(name = "parcours_id")
    Parcours parcours;

    Date dateevaluation;
}
