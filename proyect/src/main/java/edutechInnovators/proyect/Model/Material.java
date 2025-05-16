package edutechInnovators.proyect.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa la entidad Material que será persistida en la base de datos.
 *
 * Anotaciones destacadas:
 * - @Entity: Marca esta clase como una entidad JPA.
 * - @Table(name = "material"): Define el nombre de la tabla en la base de datos.
 * - @Data (de Lombok): Genera automáticamente getters, setters, equals, hashCode y toString.
 * - @NoArgsConstructor y @AllArgsConstructor (de Lombok): Generan constructores sin argumentos y con todos los argumentos respectivamente.
 */
@Entity
@Table(name = "material")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {

    /**
     * Se crea la columna y atributo id_material
     *
     * -@Id: Genera la columna como clave foranea
     * -@GeneratedValue: Genera una sequencia para la generacion de clave foranea
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_material;

    /**
     * Genera una columna url_material
     * de largo 50 y que no puede ser null
     * ademas de unique osea que no puede repetirse este dato
     */
    @Column(length = 50, nullable = false)
    private String url_material;

    /**
     * Genera una columna descripcion_material
     * de largo 80 y que no puede ser null
     * ademas de unique osea que no puede repetirse este dato
     */
    @Column(length = 80, nullable = false)
    private String descripcion_material;
}
