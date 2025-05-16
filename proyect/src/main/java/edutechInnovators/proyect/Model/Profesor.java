package edutechInnovators.proyect.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Representa la entidad Profesor que será persistida en la base de datos.
 *
 * Anotaciones destacadas:
 * - @Entity: Marca esta clase como una entidad JPA.
 * - @Table(name = "profesor"): Define el nombre de la tabla en la base de datos.
 * - @Data (de Lombok): Genera automáticamente getters, setters, equals, hashCode y toString.
 * - @NoArgsConstructor y @AllArgsConstructor (de Lombok): Generan constructores sin argumentos y con todos los argumentos respectivamente.
 */
@Entity
@Table(name = "profesor")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profesor {

    /**
     * Se crea la columna y atributo id_profesor
     *
     * -@Id: Genera la columna como clave foranea
     * -@GeneratedValue: Genera una sequencia para la generacion de clave foranea
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_profesor;

    /**
     * Genera una columna run_profesor
     * de tipo long
     * de largo 8 y que no puede ser null
     */
    @Column(length = 8, nullable = false)
    private long run_profesor;

    /**
     * Genera una columna dv_profesor
     * de largo 1 y que no puede ser null
     */
    @Column(length = 1, nullable = false)
    private String dv_profesor;

    /**
     * Genera una columna pnombre_profesor
     * de largo 25 y que no puede ser null
     */
    @Column(length = 25, nullable = false)
    private String pnombre_profesor;

    /**
     * Genera una columna snombre_profesor
     * de largo 25 y que no puede ser null
     */
    @Column(length = 25, nullable = true)
    private String snombre_profesor;

    /**
     * Genera una columna appaterno_profesor
     * de largo 25 y que no puede ser null
     */
    @Column(length = 25, nullable = false)
    private String appaterno_profesor;

    /**
     * Genera una columna apmaterno_profesor
     * de largo 25 y que no puede ser null
     */
    @Column(length = 25, nullable = false)
    private String apmaterno_profesor;

    /**
     * Genera una columna correo_profesor
     * de largo 60 y que no puede ser null
     */
    @Column(length = 60, nullable = false)
    private String correo_profesor;

    /**
     * Genera una columna contrasena_profesor
     * de largo 10 y que no puede ser null
     */
    @Column(length = 10, nullable = false)
    private String contrasena_profesor;

    /**
     * Genera una columna fecha_nacimiento_profesor
     * con tipo de dato Date
     * de largo 25 y que no puede ser null
     */
    @Column(nullable = false)
    private Date fecha_nacimiento_profesor;
}
