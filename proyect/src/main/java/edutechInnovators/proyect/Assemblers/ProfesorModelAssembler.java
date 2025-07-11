package edutechInnovators.proyect.Assemblers;

import edutechInnovators.proyect.ControllerV2.ProfesorControllerV2;
import edutechInnovators.proyect.Model.Profesor;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProfesorModelAssembler implements RepresentationModelAssembler<Profesor, EntityModel<Profesor>> {
    @Override
    public  EntityModel <Profesor> toModel(Profesor profesor){
        return  EntityModel.of(profesor,
                linkTo(methodOn(ProfesorControllerV2.class).getProfesorById(profesor.getId_profesor())).withSelfRel(),
                linkTo(methodOn(ProfesorControllerV2.class).getAllProfesores()).withRel("profesor"));
    }

}