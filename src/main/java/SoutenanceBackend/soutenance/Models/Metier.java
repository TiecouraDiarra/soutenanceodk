package SoutenanceBackend.soutenance.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "metier")
@NoArgsConstructor
@AllArgsConstructor
public class Metier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 200)
    private String nommetier;

    @NotBlank
    @Size(max = 200)
    private String imagemetier;

    @NotBlank

    private String avantage;

    @NotBlank

    private String descriptionmetier;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(  name = "parcours_metier",
            joinColumns = @JoinColumn(name = "metier_id"),
            inverseJoinColumns = @JoinColumn(name = "parcours_id"))
    private Set<Parcours> parcours= new HashSet<>();
}
