package edutechInnovators.proyect.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "profesor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_profesor;

    @Column(length = 8, nullable = false)
    private long run_profesor;

    @Column(length = 1, nullable = false)
    private String dv_profesor;

    @Column(length = 25, nullable = false)
    private String pnombre_profesor;

    @Column(length = 25, nullable = true)
    private String snombre_profesor;

    @Column(length = 25, nullable = false)
    private String appaterno_profesor;

    @Column(length = 25, nullable = false)
    private String apmaterno_profesor;

    @Column(length = 60, nullable = false)
    private String correo_profesor;

    @Column(length = 10, nullable = false)
    private String contrasena_profesor;

    @Column(nullable = false)
    private Date fecha_nacimiento_profesor;
}
