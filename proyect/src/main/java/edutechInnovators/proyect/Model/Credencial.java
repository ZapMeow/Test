package edutechInnovators.proyect.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Representa la clase Credencial que será usada en otra clase
 * @see edutechInnovators.proyect.Controller.ClienteController
 *
 * Anotaciones destacadas:
 * - @Data (de Lombok): Genera automáticamente getters, setters, equals, hashCode y toString.
 * - @NoArgsConstructor y @AllArgsConstructor (de Lombok): Generan constructores sin argumentos y con todos los argumentos respectivamente.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credencial {

    /**
     * Atributo de correo
     */
    private String correo_cliente;

    /**
     * Atributo de contraseña
     */
    private String contrasena_cliente;
}
