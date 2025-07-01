package edutechInnovators.proyect.Service;

import edutechInnovators.proyect.Model.Materia;
import edutechInnovators.proyect.Model.Material;
import edutechInnovators.proyect.Repository.MaterialRepository;
import jakarta.persistence.metamodel.SingularAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Esta clase funciona como un servicio para el proyecto que es la logica del mismo
 * @Service permite que el propio Spring lo pueda detectar para colocarlo en su sitio
 * @Transactional permite ejecutar todos los metodos especificados en esta clase
 * en caso de fallar alguno se hace un rollback para evitar perdida de datos o mal ingreso de datos
 */
@Service
@Transactional
public class MaterialService {

    /**
     * Inyecta una instancia del atributo especificado
     */
    @Autowired
    private MaterialRepository materialRepository;

    /**
     * Metodo para encontrar todas las filas insertadas en dicha tabla
     * @return devuelve todas las filas en una lista
     */
    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    /**
     * Inserta una fila en la tabla especificada
     * @param material el parametro el cual se va a insertar
     * @return se devuelve el cliente insertado
     */
    public Material save(Material material) {
        materialRepository.save(material);
        return material;
    }

    /**
     * Encuentra una fila mediante un atributo especifico
     * @param id es el parametro por el cual se busca una fila
     * @return la fila encontrada mediante el filtro
     */
    public Material findById(long id) {
        return materialRepository.findById(id).get();
    }

    /**
     * Encuentra la fila la cual se va a reemplazar su datos
     * @param material es el parametro el cual se va a actualizar
     * @return el parametro reemplazado
     */
    public Material update(Material material) {
        return materialRepository.save(material);
    }

    /**
     * Borra una fila en la tabla especificada
     * @param material el parametro por el cual se va a borrar la fila
     */
    public void delete(Material material) {
        materialRepository.delete(material);
    }



}
