package edutechInnovators.proyect.Repository;

import edutechInnovators.proyect.Model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Esta clase esta relacionada con la modificacion en la base de datos
 * @see JpaRepository la clase que permita las funcionalidades CRUD.
 * Ademas este permite crear mas funcionalidades dependiendo de los metodos que
 * quieras escribir
 *
 * Al tener la anotacion @Repository spring lo detecta para tener las excepciones
 * al ingresar a la base de datos de forma erronea
 */
@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
}
