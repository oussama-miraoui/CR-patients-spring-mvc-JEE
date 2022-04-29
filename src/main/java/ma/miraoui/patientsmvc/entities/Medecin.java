package ma.miraoui.patientsmvc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "this field must not be empty!")
    private String nom;
    @NotEmpty(message = "this field must not be empty!")
    private String email;
    @NotEmpty(message = "this field must not be empty!")
    private String specialite;
    //@OneToMany(mappedBy = "medecin", fetch = FetchType.LAZY)
    //private Collection<RendezVous> rendezVous;
}
