package edutechInnovators.proyect.ControllerV2;

import edutechInnovators.proyect.Assemblers.ClienteModelAssembler;
import edutechInnovators.proyect.Model.Cliente;
import edutechInnovators.proyect.Service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/edutechinnovations/api/v2/cliente")
@Tag(name = "Clientes", description = "Peticiones de clientes")
public class ClienteControllerV2 {

    @Autowired
    private ClienteService  clienteService;

    @Autowired
    private ClienteModelAssembler clienteModelAssembler;



    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener clientes", description = "Obtienes una lista con todos los clientes tantos activos como inactivos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado una lista de clientes",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "204", description = "La solicitud esta bien pero no se han encontrado datos")
    })
    public CollectionModel<EntityModel<Cliente>>getAllClientes(){
        System.out.println("getAllCliente");
        List<EntityModel<Cliente>>clientes = clienteService.findAll().stream()
                .map(clienteModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(clientes, linkTo(methodOn(ClienteControllerV2.class).getAllClientes()).withSelfRel());

    }
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
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public  EntityModel<Cliente> getClienteById(@PathVariable long id ){
        System.out.println("getClienteById");
        Cliente cliente = clienteService.findById(id);
        return  clienteModelAssembler.toModel(cliente);

    }

    @Operation(summary = "Guardar un clinete", description = "Guarda un nuevo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se ha creado un nuevo cliente",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Cliente.class))),
            @ApiResponse(responseCode = "400", description = "El formato del body esta mal estructurado",
                    content = @Content)
    })
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public  ResponseEntity<EntityModel<Cliente>>createCliente(@RequestBody Cliente cliente){
        System.out.println("createCliente");
        Cliente newClinete =clienteService.save(cliente);
                return ResponseEntity
                        .created(linkTo(methodOn(ClienteControllerV2.class).getClienteById(newClinete.getId_cliente())).toUri())
                         .body(clienteModelAssembler.toModel(newClinete));
    }

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
    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cliente>> updateCliente(@PathVariable int id, @RequestBody @NotNull Cliente cliente){
        System.out.println("updateCliente");
        cliente.setId_cliente(id);
        Cliente updateCliente = clienteService.save(cliente);
        return ResponseEntity.ok(clienteModelAssembler.toModel(updateCliente));
    }

    @Operation(summary = "Borrar usuario", description = "Borra un usuario por la id especificada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Se ha borrado un usuario exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No se ha colocado la id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro el usuario",
                    content = @Content)
    })
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteCliente(@PathVariable int id){
        System.out.println("deleteCursos");
        Cliente cliente = clienteService.findById(id);
        clienteService.delete(cliente);
        return ResponseEntity.noContent().build();
    }


}
