package edutechInnovators.proyect.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Representa la entidad Inscripcion que será persistida en la base de datos.
 *
 * Anotaciones destacadas:
 * - @Entity: Marca esta clase como una entidad JPA.
 * - @Table(name = "inscripcion"): Define el nombre de la tabla en la base de datos.
 * - @Data (de Lombok): Genera automáticamente getters, setters, equals, hashCode y toString.
 * - @NoArgsConstructor y @AllArgsConstructor (de Lombok): Generan constructores sin argumentos y con todos los argumentos respectivamente.
 */
@Entity
@Table(name = "inscripcion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inscripcion {

    /**
     * Se crea la columna y atributo id_ins
     *
     * -@Id: Genera la columna como clave foranea
     * -@GeneratedValue: Genera una sequencia para la generacion de clave foranea
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ins;

    /**
     * Genera una columna fecha_inscripcion_ins
     * de tipo Date y no puede ser null
     */
    @Column(nullable = false)
    private Date fecha_inscripcion_ins;


}
