package edutechInnovators.proyect.Service;

import edutechInnovators.proyect.Model.Curso;
import edutechInnovators.proyect.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Esta clase funciona como un servicio para el proyecto que es la logica del mismo
 * @Service permite que el propio Spring lo pueda detectar para colocarlo en su sitio
 * @Transactional permite ejecutar todos los metodos especificados en esta clase
 * en caso de fallar alguno se hace un rollback para evitar perdida de datos o mal ingreso de datos
 */
@Repository
@Transactional
public class CursoService {

    /**
     * Inyecta una instancia del atributo especificado
     */
    @Autowired
    private CursoRepository cursoRepository;

    /**
     * Metodo para encontrar todas las filas insertadas en dicha tabla
     * @return devuelve todas las filas en una lista
     */
    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    /**
     * Encuentra una fila mediante un atributo especifico
     * @param id es el parametro por el cual se busca una fila
     * @return la fila encontrada mediante el filtro
     */
    public Curso findById(long id) {
        return cursoRepository.findById(id).get();
    }

    /**
     * Inserta una fila en la tabla especificada
     * @param curso el parametro el cual se va a insertar
     * @return se devuelve el cliente insertado
     */
    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    /**
     * Borra una fila en la tabla especificada
     * @param curso el parametro por el cual se va a borrar la fila
     */
    public void delete(Curso curso) {
        cursoRepository.delete(curso);
    }

}
