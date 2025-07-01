package edutechInnovators.proyect.Assemblers;

import edutechInnovators.proyect.ControllerV2.MateriaControllerV2;
import edutechInnovators.proyect.Model.Materia;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MateriaModelAssembler implements RepresentationModelAssembler<Materia, EntityModel<Materia>> {
    @Override
    public  EntityModel <Materia> toModel(Materia materia){
        return  EntityModel.of(materia,
                linkTo(methodOn(MateriaControllerV2.class).getMaterialById(materia.getId())).withSelfRel(),
                linkTo(methodOn(MateriaControllerV2.class).getClass()).withRel("materia"));
    }

}
