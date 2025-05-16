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

    /**
     * Se crea la columna y atributo id_cliente
     *
     * -@Id: Genera la columna como clave foranea
     * -@GeneratedValue: Genera una sequencia para la generacion de clave foranea
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cliente;

    /**
     * Genera una columna run_cliente
     * de largo 8 y no puede ser null
     * con tipo de dato long
     */
    @Column(length = 8, nullable = false)
    private long run_cliente;

    /**
     * Genera una columna dv_cliente
     * de largo 1 y que no puede ser null
     */
    @Column(length = 1, nullable = false)
    private String dv_cliente;

    /**
     * Genera una columna pnombre_cliente
     * de largo 25 y que no puede ser null
     */
    @Column(length = 25, nullable = false)
    private String pnombre_cliente;

    /**
     * Genera una columna snombre_cliente
     * de largo 25 y que puede ser null
     */
    @Column(length = 25, nullable = true)
    private String snombre_cliente;

    /**
     * Genera una columna appaterno_cliente
     * de largo 25 y que no puede ser null
     */
    @Column(length = 25, nullable = false)
    private String appaterno_cliente;

    /**
     * Genera una columna apmaterno_cliente
     * de largo 25 y que no puede ser null
     */
    @Column(length = 25, nullable = false)
    private String apmaterno_cliente;

    /**
     * Genera una columna correo_cliente
     * de largo 60 y que no puede ser null
     */
    @Column(length = 60, nullable = false)
    private String correo_cliente;

    /**
     * Genera una columna contrasena_cliente
     * de largo 10 y que no puede ser nulo
     */
    @Column(length = 10, nullable = false)
    private String contrasena_cliente;

    /**
     * Genera una columna fecha_nacimiento_cliente
     * que no puede ser nulo y su tipo de dato es
     * Date
     */
    @Column(nullable = false)
    private Date fecha_nacimiento_cliente;

    /**
     * Genera una columna activo_cliente
     * que no puede ser nulo y de tipo booleano
     */
    @Column(nullable = false)
    private boolean activo_cliente;

}
