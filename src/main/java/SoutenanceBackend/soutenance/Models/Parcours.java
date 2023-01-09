package SoutenanceBackend.soutenance.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
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
    @Size(max = 100)
    @Email
    private String description;

    @NotBlank
    @Size(max = 120)
    private String avantage;

    @NotBlank
    @Size(max = 120)
    private String conseil;

    @NotBlank
    @Size(max = 120)
    private String domaine;

    @NotBlank
    @Size(max = 120)
    private String filiere;

    @NotBlank
    @Size(max = 120)
    private String imageparcours;

    @ManyToOne
    //@JsonIgnore
    @JoinColumn(name = "id_niveauparcours")
    private Niveauparcours niveauparcours;

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
}
