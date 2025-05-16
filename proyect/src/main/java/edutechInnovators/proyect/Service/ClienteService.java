package edutechInnovators.proyect.Service;

import edutechInnovators.proyect.Model.Cliente;
import edutechInnovators.proyect.Repository.ClienteRepository;
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
public class ClienteService {

    /**
     * Inyecta una instancia del atributo especificado
     */
    @Autowired
    private ClienteRepository clienteRepository;

    /**
     * Metodo para encontrar todas las filas insertadas en dicha tabla
     * @return devuelve todas las filas en una lista
     */
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    /**
     * Encuentra una fila mediante un atributo especifico
     * @param id es el parametro por el cual se busca una fila
     * @return la fila encontrada mediante el filtro
     */
    public Cliente findById(long id) {
        return clienteRepository.findById(id).get();
    }

    /**
     * Inserta una fila en la tabla especificada
     * @param cliente el parametro el cual se va a insertar
     * @return se devuelve el cliente insertado
     */
    public Cliente save(Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }

    /**
     * Borra una fila en la tabla especificada
     * @param cliente el parametro por el cual se va a borrar la fila
     */
    public void delete(Cliente cliente) {
        clienteRepository.delete(cliente);
    }

}
