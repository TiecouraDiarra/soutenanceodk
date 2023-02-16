package SoutenanceBackend.soutenance.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//@Entity
@Getter
@Setter
//@Table(name = "parammatiere")
@NoArgsConstructor
@AllArgsConstructor
public class ParametreMatiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Long valeurPlafond;

    @OneToOne
    //@JsonIgnore
    @JoinColumn(name = "id_matierequestion")
    private MatiereQuestion matiereQuestion;
}
