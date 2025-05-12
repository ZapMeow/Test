package edutechInnovators.proyect.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "material")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_material;

    @Column(length = 50, nullable = false)
    private String url_material;

    @Column(length = 80, nullable = false)
    private String descripcion_material;
}
