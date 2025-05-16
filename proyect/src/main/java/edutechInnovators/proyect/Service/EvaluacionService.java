package edutechInnovators.proyect.Service;

import edutechInnovators.proyect.Model.Evaluacion;
import edutechInnovators.proyect.Repository.EvaluacionRepository;
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
public class EvaluacionService {

    /**
     * Inyecta una instancia del atributo especificado
     */
    @Autowired
    private EvaluacionRepository evaluacionRepository;

    /**
     * Metodo para encontrar todas las filas insertadas en dicha tabla
     * @return devuelve todas las filas en una lista
     */
    public List<Evaluacion> findAll() {
        return evaluacionRepository.findAll();
    }

    /**
     * Encuentra una fila mediante un atributo especifico
     * @param id es el parametro por el cual se busca una fila
     * @return la fila encontrada mediante el filtro
     */
    public Evaluacion findById(int id) {
        return evaluacionRepository.findById(id).get();
    }

    /**
     * Inserta una fila en la tabla especificada
     * @param evaluacion el parametro el cual se va a insertar
     * @return se devuelve el cliente insertado
     */
    public Evaluacion save(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    /**
     * Borra una fila en la tabla especificada
     * @param evaluacion el parametro por el cual se va a borrar la fila
     */
    public void delete(Evaluacion evaluacion) {
        evaluacionRepository.delete(evaluacion);
    }

}
