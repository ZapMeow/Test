package edutechInnovators.proyect.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "evaluacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_eva;

    @Column(length = 50, nullable = false)
    private String nombre_eva;

    @Column(length = 5, scale = 2, nullable = false)
    private double ponderacion_eva;
}
