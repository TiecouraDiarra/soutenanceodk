package SoutenanceBackend.soutenance.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ModifierMotDePasse {
    private String ancienmdp;
    private String nouveaumdp;
    private String confirmNewmdp;


}
