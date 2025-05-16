package edutechInnovators.proyect.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa la entidad Evaluacion que será persistida en la base de datos.
 *
 * Anotaciones destacadas:
 * - @Entity: Marca esta clase como una entidad JPA.
 * - @Table(name = "evaluacion"): Define el nombre de la tabla en la base de datos.
 * - @Data (de Lombok): Genera automáticamente getters, setters, equals, hashCode y toString.
 * - @NoArgsConstructor y @AllArgsConstructor (de Lombok): Generan constructores sin argumentos y con todos los argumentos respectivamente.
 */
@Entity
@Table(name = "evaluacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluacion {

    /**
     * Se crea la columna y atributo id_eva
     *
     * -@Id: Genera la columna como clave foranea
     * -@GeneratedValue: Genera una sequencia para la generacion de clave foranea
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_eva;

    /**
     * Genera una columna nombre_eva
     * de largo 50 y que no puede ser null
     */
    @Column(length = 50, nullable = false)
    private String nombre_eva;

    /**
     * Genera una columna ponderacion_eva
     * con dato double
     * de largo 5 y que 2 son decimales ademas
     * de no poder ser null
     */
    @Column(length = 5, scale = 2, nullable = false)
    private double ponderacion_eva;
}
