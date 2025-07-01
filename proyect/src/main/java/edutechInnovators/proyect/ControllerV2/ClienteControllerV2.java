package edutechInnovators.proyect.ControllerV2;

import edutechInnovators.proyect.Assemblers.ClienteModelAssembler;
import edutechInnovators.proyect.Model.Cliente;
import edutechInnovators.proyect.Service.ClienteService;
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
public class ClienteControllerV2 {

    @Autowired
    private ClienteService  clienteService;

    @Autowired
    private ClienteModelAssembler clienteModelAssembler;



    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Cliente>>getAllCliente(){
        System.out.println("getAllCliente");
        List<EntityModel<Cliente>>clientes = clienteService.findAll().stream()
                .map(clienteModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(clientes, linkTo(methodOn(ClienteControllerV2.class).getAllCliente()).withSelfRel());

    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public  EntityModel<Cliente> getClienteById(@PathVariable long id ){
        System.out.println("getClienteById");
        Cliente cliente = clienteService.findById(id);
        return  clienteModelAssembler.toModel(cliente);

    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public  ResponseEntity<EntityModel<Cliente>>createCliente(@RequestBody Cliente cliente){
        System.out.println("createCliente");
        Cliente newClinete =clienteService.save(cliente);
                return ResponseEntity
                        .created(linkTo(methodOn(ClienteControllerV2.class).getClienteById(newClinete.getId_cliente())).toUri())
                         .body(clienteModelAssembler.toModel(newClinete));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Cliente>> updateCliente(@PathVariable int id, @RequestBody @NotNull Cliente cliente){
        System.out.println("updateCliente");
        cliente.setId_cliente(id);
        Cliente updateCliente = clienteService.save(cliente);
        return ResponseEntity.ok(clienteModelAssembler.toModel(updateCliente));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteCurso(@PathVariable int id){
        System.out.println("deleteCursos");
        Cliente cliente = clienteService.findById(id);
        clienteService.delete(cliente);
        return ResponseEntity.noContent().build();
    }


}
