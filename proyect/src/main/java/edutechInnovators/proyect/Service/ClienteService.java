package edutechInnovators.proyect.Service;

import edutechInnovators.proyect.Model.Cliente;
import edutechInnovators.proyect.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(long id) {
        return clienteRepository.findById(id).get();
    }

    public Cliente save(Cliente cliente) {
        clienteRepository.save(cliente);
        return cliente;
    }

    public void delete(Cliente cliente) {
        clienteRepository.delete(cliente);
    }

}
