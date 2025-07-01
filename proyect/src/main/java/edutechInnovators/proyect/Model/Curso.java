package edutechInnovators.proyect.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Representa la entidad Curso que será persistida en la base de datos.
 *
 * Anotaciones destacadas:
 * - @Entity: Marca esta clase como una entidad JPA.
 * - @Table(name = "curso"): Define el nombre de la tabla en la base de datos.
 * - @Data (de Lombok): Genera automáticamente getters, setters, equals, hashCode y toString.
 * - @NoArgsConstructor y @AllArgsConstructor (de Lombok): Generan constructores sin argumentos y con todos los argumentos respectivamente.
 */
@Entity
@Table(name = "curso")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Curso {

    /**
     * Se crea la columna y atributo id_curso
     *
     * -@Id: Genera la columna como clave foranea
     * -@GeneratedValue: Genera una sequencia para la generacion de clave foranea
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_curso;

    /**
     * Genera una columna nombre_curso
     * de largo 50 y que no puede ser null
     */
    @Column(length = 50, nullable = false)
    private String nombre_curso;

    /**
     * Genera una columna fecha_creacion_curso
     * con tipo de dato Date
     */
    @Column(nullable = false)
    private Date fecha_creacion_curso;


}
