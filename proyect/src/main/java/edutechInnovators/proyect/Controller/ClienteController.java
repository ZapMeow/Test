package edutechInnovators.proyect.Controller;

import edutechInnovators.proyect.Model.Cliente;
import edutechInnovators.proyect.Model.Credencial;
import edutechInnovators.proyect.Service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/edutechinnovations/api/v1/cliente")
@Tag(name = "Clientes", description = "Peticiones de clientes")
public class ClienteController {


    @Autowired
    private ClienteService clienteService;


    @GetMapping
    @Operation(summary = "Obtener clientes", description = "Obtienes una lista con todos los clientes tantos activos como inactivos")
    public ResponseEntity<List<Cliente>> getAllClientes() {
        System.out.println("getAllClientes");
        List<Cliente> clientes = clienteService.findAll();
        if (clientes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientes);
    }


    @PostMapping
    @Operation(summary = "Guardar un clinete", description = "Guarda un nuevo cliente")
    public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente) {
        System.out.println("saveCliente");
        Cliente newCliente = clienteService.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCliente);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente", description = "Busca y devuelve un cliente especificado con la id")
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        System.out.println("getClienteById");
        try{
            Cliente cliente = clienteService.findById(id);
            return ResponseEntity.ok(cliente);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    @Operation(summary = "actualizar cliente", description = "Toma un cliente existente y cambia sus datos por el id especificado")
    public ResponseEntity<Cliente> updateCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        System.out.println("updateCliente");
        try{
            Cliente cliente1 = clienteService.findById(id);
            cliente1.setId_cliente(id);
            cliente1.setRun_cliente(cliente.getRun_cliente());
            cliente1.setDv_cliente(cliente.getDv_cliente());
            cliente1.setPnombre_cliente(cliente.getPnombre_cliente());
            cliente1.setSnombre_cliente(cliente.getSnombre_cliente());
            cliente1.setAppaterno_cliente(cliente.getAppaterno_cliente());
            cliente1.setApmaterno_cliente(cliente.getApmaterno_cliente());
            cliente1.setCorreo_cliente(cliente.getCorreo_cliente());
            cliente1.setContrasena_cliente(cliente.getContrasena_cliente());
            cliente1.setFecha_nacimiento_cliente(cliente.getFecha_nacimiento_cliente());
            cliente1.setActivo_cliente(cliente.isActivo_cliente());

            clienteService.save(cliente1);

            return ResponseEntity.ok(cliente1);

        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    @PostMapping("/sesion")
    @Operation(summary = "Sesion", description = "Verifica si los datos especificados son del cliente")
    public ResponseEntity<String> isValidSesion(@RequestBody Credencial credencial) {
        System.out.println("isValidSesion");

        List<Cliente> clientes = clienteService.findAll();
        for (Cliente cliente : clientes) {
            if (cliente.getCorreo_cliente().equals(credencial.getCorreo_cliente()) && cliente.getContrasena_cliente().equals(credencial.getContrasena_cliente())) {
                return ResponseEntity.ok("sesion concretada\nBienvenido " + cliente.getPnombre_cliente() + " " + cliente.getAppaterno_cliente());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario o contraseña incorrecta");

    }




}
