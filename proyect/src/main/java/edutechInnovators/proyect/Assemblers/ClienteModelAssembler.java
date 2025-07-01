package edutechInnovators.proyect.Assemblers;

import edutechInnovators.proyect.ControllerV2.ClienteControllerV2;
import edutechInnovators.proyect.Model.Cliente;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClienteModelAssembler implements RepresentationModelAssembler<Cliente, EntityModel<Cliente>> {


    @Override
    public EntityModel <Cliente> toModel(Cliente cliente){
        return EntityModel.of(cliente,
                linkTo(methodOn(ClienteControllerV2.class).getClienteById(cliente.getId_cliente())).withSelfRel(),
                linkTo(methodOn(ClienteControllerV2.class).getAllCliente()).withRel("Cliente"));
    }



}
