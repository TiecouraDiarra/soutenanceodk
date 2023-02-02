package SoutenanceBackend.soutenance.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "questionprof")
@NoArgsConstructor
@AllArgsConstructor
public class QuestionProfessionnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;


    /*@OneToMany(mappedBy = "questionprof", cascade = CascadeType.ALL)
    private List<Answer> answers;*/

}
