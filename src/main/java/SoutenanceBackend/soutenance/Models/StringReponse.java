package SoutenanceBackend.soutenance.Models;

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
//@Table(name = "stringreponse")
@NoArgsConstructor
@AllArgsConstructor
public class StringReponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*@NotBlank
    @Size(max = 200)*/
    private String Stringreponse;
}
