package edutechInnovators.proyect.Service;

import edutechInnovators.proyect.Model.Profesor;
import edutechInnovators.proyect.Repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase funciona como un servicio para el proyecto que es la logica del mismo
 * @Service permite que el propio Spring lo pueda detectar para colocarlo en su sitio
 * @Transactional permite ejecutar todos los metodos especificados en esta clase
 * en caso de fallar alguno se hace un rollback para evitar perdida de datos o mal ingreso de datos
 */
@Service
@Transactional
public class ProfesorService {

    /**
     * Inyecta una instancia del atributo especificado
     */
    @Autowired
    private ProfesorRepository profesorRepository;

    /**
     * Metodo para encontrar todas las filas insertadas en dicha tabla
     * @return devuelve todas las filas en una lista
     */
    public List<Profesor> getProfesores() {
        return profesorRepository.findAll();
    }

    /**
     * Encuentra una fila mediante un atributo especifico
     * @param id es el parametro por el cual se busca una fila
     * @return la fila encontrada mediante el filtro
     */
    public Profesor getProfesorById(long id) {
        return profesorRepository.findById(id).get();
    }

    /**
     * Inserta una fila en la tabla especificada
     * @param profesor el parametro el cual se va a insertar
     * @return se devuelve el cliente insertado
     */
    public Profesor saveProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    /**
     * Encuentra la fila la cual se va a reemplazar su datos
     * @param profesor es el parametro el cual se va a actualizar
     * @return el parametro reemplazado
     */
    public Profesor updateProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    /**
     * Borra una fila en la tabla especificada
     * @param id el parametro por el cual se va a borrar la fila
     */
    public void deleteProfesor(long id) {
        profesorRepository.deleteById(id);
    }


}
