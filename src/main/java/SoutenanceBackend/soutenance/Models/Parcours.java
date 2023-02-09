package SoutenanceBackend.soutenance.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "parcours")
@NoArgsConstructor
@AllArgsConstructor
public class Parcours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 100)
    private String nomparcours;

    @NotBlank
    private String description;

    @NotBlank
    private String titre;

    @NotBlank
    private String conseil;

    @NotBlank
    private String type;

    @NotBlank
    private String admission;

    @NotBlank
    private String duree;

    @NotBlank
    private String filiere;

    @NotBlank
    @Size(max = 120)
    private String imageparcours;

    @ManyToOne
    //@JsonIgnore
    @JoinColumn(name = "id_niveauparcours")
    private Niveauparcours niveauparcours;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "parcours")
    List<Metier> metiers = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "parcours")
    List<Matiere> matieres = new ArrayList<>();

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "parcours_matieres",
            joinColumns = @JoinColumn(name = "parcours_id"),
            inverseJoinColumns = @JoinColumn(name = "matieres_id"))
    private Set<Matiere> matiere ;*/

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "parcours_metier",
            joinColumns = @JoinColumn(name = "parcours_id"),
            inverseJoinColumns = @JoinColumn(name = "metier_id"))
    private Set<MetierRepository> metier ;*/

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "parcours_metier",
            joinColumns = @JoinColumn(name = "parcours_id"),
            inverseJoinColumns = @JoinColumn(name = "metier_id"))
    List<Metier> metiers = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "parcours_matiere",
            joinColumns = @JoinColumn(name = "parcours_id"),
            inverseJoinColumns = @JoinColumn(name = "matiere_id"))
    List<Matiere> matieres = new ArrayList<>();*/
}
