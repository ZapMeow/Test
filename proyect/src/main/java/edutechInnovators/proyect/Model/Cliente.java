package edutechInnovators.proyect.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cliente;

    @Column(length = 8, nullable = false)
    private long run_cliente;

    @Column(length = 1, nullable = false)
    private String dv_cliente;

    @Column(length = 25, nullable = false)
    private String pnombre_cliente;

    @Column(length = 25, nullable = true)
    private String snombre_cliente;

    @Column(length = 25, nullable = false)
    private String appaterno_cliente;

    @Column(length = 25, nullable = false)
    private String apmaterno_cliente;

    @Column(length = 60, nullable = false)
    private String correo_cliente;

    @Column(length = 10, nullable = false)
    private String contrasena_cliente;

    @Column(nullable = false)
    private Date fecha_nacimiento_cliente;

    @Column(nullable = false)
    private boolean activo_cliente;

}
