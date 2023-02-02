package SoutenanceBackend.soutenance.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "matiere")
@NoArgsConstructor
@AllArgsConstructor
public class Matiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String nommatiere;

    @NotBlank
    @Size(max = 200)
    private String descriptionmatiere;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "matieres_parcours",
            joinColumns = @JoinColumn(name = "matieres_id"),
            inverseJoinColumns = @JoinColumn(name = "parcours_id"))
    private Set<Parcours> parcours = new HashSet<>();
    //private Collection<Parcours> parcours = new ArrayList<>();
    //private Parcours parcours;

}
