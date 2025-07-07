package edutechInnovators.proyect;

import edutechInnovators.proyect.Model.Cliente;
import edutechInnovators.proyect.Model.Curso;
import edutechInnovators.proyect.Repository.ClienteRepository;
import edutechInnovators.proyect.Service.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;

    @MockBean
    private ClienteRepository clienteRepository;


    @Test
    public void TestAllClienteMethods(){
        testFindAllClientes();
        testFindClientesById();
        testSaveCliente();
        testDeleteClienteById();
    }

    @Test
    public void testFindAllClientes() {

        Date currentDate = new Date();
        when(clienteRepository.findAll()).thenReturn(List.of(new Cliente(1, 21141971, "7", "balatro", "lua", "balatrez", "baraja plasmatica", "balatro@gmail.com", "12345678", currentDate, true)));
        List<Cliente> clienteList = clienteRepository.findAll();
        assertNotNull(clienteList);
        assertEquals(1,clienteList.size());

    }

    @Test
    public void testFindClientesById(){

        long id = 1;
        Date currentDate = new Date();
        Cliente cliente = new Cliente(1, 21141971, "7", "balatro", "lua", "balatrez", "baraja plasmatica", "balatro@gmail.com", "12345678", currentDate, true);
        when(clienteRepository.findById(id)).thenReturn(Optional.of(cliente));
        Cliente found = clienteService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId_cliente());

    }


    @Test
    public void testSaveCliente(){

        Date currentDate = new Date();

        Cliente cliente = new Cliente(1, 21141971, "7", "balatro", "lua", "balatrez", "baraja plasmatica", "balatro@gmail.com", "12345678", currentDate, true);
        when(clienteRepository.save(cliente)).thenReturn(cliente);
        Cliente saved = clienteService.save(cliente);
        assertNotNull(saved);
        assertEquals(1,saved.getId_cliente());
        assertEquals(21141971, saved.getRun_cliente());
        assertEquals("7", saved.getDv_cliente());
        assertEquals("balatro", saved.getPnombre_cliente());
        assertEquals("lua", saved.getSnombre_cliente());
        assertEquals("balatrez", saved.getAppaterno_cliente());
        assertEquals("baraja plasmatica", saved.getApmaterno_cliente());
        assertEquals("balatro@gmail.com", saved.getCorreo_cliente());
        assertEquals("12345678", saved.getContrasena_cliente());
        assertEquals(currentDate, saved.getFecha_nacimiento_cliente());
        assertEquals(true, saved.isActivo_cliente());

    }

    @Test
    public void testDeleteClienteById(){

        long id = 1;
        Date currentDate = new Date();
        Cliente cliente = new Cliente(1, 21141971, "7", "balatro", "lua", "balatrez", "baraja plasmatica", "balatro@gmail.com", "12345678", currentDate, true);
        doNothing().when(clienteRepository).delete(cliente);
        clienteService.delete(cliente);
        verify(clienteRepository, times(1)).delete(cliente);

    }

}
