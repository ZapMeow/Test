package edutechInnovators.proyect.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "inscripcion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ins;

    @Column(nullable = false)
    private Date fecha_inscripcion_ins;

    @OneToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;
}
