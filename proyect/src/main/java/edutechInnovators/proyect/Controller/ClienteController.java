package edutechInnovators.proyect.Controller;

import edutechInnovators.proyect.Model.Cliente;
import edutechInnovators.proyect.Model.Credencial;
import edutechInnovators.proyect.Model.Evaluacion;
import edutechInnovators.proyect.Model.Profesor;
import edutechInnovators.proyect.Service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Esta clase es la encargada de las peticiones REST y el propio funcionamiento de estas
 *
 * La anotacion @RestController indica que esta clase va a actuar como controlador de las
 * peticiones REST
 * La anotacion @RequestMapping indica en que ruta estara dichos servicios para su interaccion con
 * las peticiones REST o tambien endpoint
 */
@RestController
@RequestMapping("/edutechinnovations/api/v1/cliente")
@Tag(name = "Clientes", description = "Peticiones de clientes")
public class ClienteController {

    /**
     * La anotacion @Autowired permide una instancia de dicho atributo sin necesidad de hacerlo
     * uno mismo
     */
    @Autowired
    private ClienteService clienteService;

    /**
     * Esta funcion permite obtener todos los componentes de dicha tabla
     * La anotacion @GetMapping permite recibir peticiones GET desde una pagina con
     * un path especifico
     * ResponseEntity permite una escritura y estructuracion de respuesta en formato HTTP
     * @return el retorno depende de la respuesta final estructurandola a gusto.
     */
    @GetMapping
    @Operation(summary = "Obtener clientes", description = "Obtienes una lista con todos los clientes tantos activos como inactivos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado una lista de clientes",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "204", description = "La solicitud esta bien pero no se han encontrado datos")
    })
    public ResponseEntity<List<Cliente>> getAllClientes() {
        System.out.println("getAllClientes");
        List<Cliente> clientes = clienteService.findAll();
        if (clientes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clientes);
    }

    /**
     * Esta funcin permite insertar datos dependiendo lo especificado
     * La anotacion @PostMapping permite recibir peticiones POST desde una pagina con
     * un path especifico
     * @RequestBody permite rescatar el body de la peticion
     * ResponseEntity permite una escritura y estructuracion de respuesta en formato HTTP
     * @return el retorno depende de la respuesta final estructurandola a gusto.
     */
    @PostMapping
    @Operation(summary = "Guardar un clinete", description = "Guarda un nuevo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se ha creado un nuevo cliente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "400", description = "El formato del body esta mal estructurado",
                    content = @Content)
    })
    public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente) {
        System.out.println("saveCliente");
        Cliente newCliente = clienteService.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCliente);
    }

    /**
     * Esta funcion permite obtener un dato con una variable especifica
     * La anotacion @GetMapping permite recibir peticiones GET desde una pagina con
     * un path especifico
     * @PathVariable permite rescatar una variable desde lo que esta escrito en la ruta
     * ResponseEntity permite una escritura y estructuracion de respuesta en formato HTTP
     * @return el retorno depende de la respuesta final estructurandola a gusto.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Obtener cliente", description = "Busca y devuelve un cliente especificado con la id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado un cliente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "401", description = "No se inserto la id para la busqueda",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "La id especificada no se encuentra",
                    content = @Content)
    })
    public ResponseEntity<Cliente> getClienteById(@PathVariable Integer id) {
        System.out.println("getClienteById");
        try{
            Cliente cliente = clienteService.findById(id);
            return ResponseEntity.ok(cliente);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Esta funcion permite actualizar dicho dato especificado
     * La anotacion @PutMapping permite recibir peticiones PUT desde una pagina con
     * un path especifico
     * @PathVariable permite rescatar una variable desde lo que esta escrito en la ruta
     * @RequestBody permite rescatar el body de la peticion
     * ResponseEntity permite una escritura y estructuracion de respuesta en formato HTTP
     * @return el retorno depende de la respuesta final estructurandola a gusto.
     */
    @PutMapping("/{id}")
    @Operation(summary = "actualizar cliente", description = "Toma un cliente existente y cambia sus datos por el id especificado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha actualizado un cliente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "401", description = "El cuerpo tiene un mal formato o no hay id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro el cliente para actualizar",
                    content = @Content)
    })
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

    /**
     * Esta funcion permite verificar la sesion de un usuario
     * La anotacion @PostMapping permite recibir peticiones POST desde una pagina con
     * un path especifico
     * @RequestBody permite rescatar el body de la peticion
     * ResponseEntity permite una escritura y estructuracion de respuesta en formato HTTP
     * @return el retorno depende de la respuesta final estructurandola a gusto.
     */
    @PostMapping("/sesion")
    @Operation(summary = "Sesion", description = "Verifica si los datos especificados son del cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Las credenciales son correctas",
                content = @Content),
            @ApiResponse(responseCode = "401", description = "Las credenciales o el cuerpo de la solicitud son incorrectos",
                content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro ningun dato dado",
                content = @Content)
    })
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

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar usuario", description = "Borra un usuario por la id especificada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Se ha borrado un usuario exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No se ha colocado la id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario",
                    content = @Content)
    })
    public ResponseEntity<?> deleteCliente(@PathVariable long id){

        try{
            Cliente cliente = clienteService.findById(id);
            clienteService.delete(cliente);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }




}
