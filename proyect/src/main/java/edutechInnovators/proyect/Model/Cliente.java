package edutechInnovators.proyect.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * Representa la entidad Cliente que será persistida en la base de datos.
 *
 * Anotaciones destacadas:
 * - @Entity: Marca esta clase como una entidad JPA.
 * - @Table(name = "cliente"): Define el nombre de la tabla en la base de datos.
 * - @Data (de Lombok): Genera automáticamente getters, setters, equals, hashCode y toString.
 * - @NoArgsConstructor y @AllArgsConstructor (de Lombok): Generan constructores sin argumentos y con todos los argumentos respectivamente.
 */
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

    @Column(length = 30, nullable = false)
    private String pnombre_cliente;

    @Column(length = 30, nullable = true)
    private String snombre_cliente;

    @Column(length = 30, nullable = false)
    private String appaterno_cliente;

    @Column(length = 30, nullable = false)
    private String apmaterno_cliente;

    @Column(length = 20, nullable = false)
    private String nombreUsuario;

    @Column(length = 60, nullable = false)
    private String correo_cliente;

    @Column(length = 10, nullable = false)
    private String contrasena_cliente;

    @Column(nullable = false)
    private Date fecha_nacimiento_cliente;

    @Column(nullable = false)
    private boolean activo_cliente;

    //FOREING KEY
    @Column(length = 3, nullable = false)
    private int id_comuna;

}
