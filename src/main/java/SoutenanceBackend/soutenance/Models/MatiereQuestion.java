package SoutenanceBackend.soutenance.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@Table(name = "matierequestion")
@NoArgsConstructor
@AllArgsConstructor
public class MatiereQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String matierequestion;

    @ManyToOne
    @JoinColumn(name = "id_typematiere")
    private TypeMatiere typematiere;
}
