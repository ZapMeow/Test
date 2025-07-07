package edutechInnovators.proyect.Assemblers;

import edutechInnovators.proyect.ControllerV2.EvaluacionControllerV2;
import edutechInnovators.proyect.ControllerV2.InscripcionControllerV2;
import edutechInnovators.proyect.Model.Evaluacion;
import edutechInnovators.proyect.Model.Inscripcion;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class InscripcionModelAssembler implements RepresentationModelAssembler<Inscripcion, EntityModel<Inscripcion>> {

    @Override
    public EntityModel<Inscripcion> toModel(Inscripcion inscripcion){
        return  EntityModel.of(inscripcion,
                linkTo(methodOn(InscripcionControllerV2.class).getInscripcionById(inscripcion.getId_ins())).withSelfRel(),
                linkTo(methodOn(InscripcionControllerV2.class).getAllInscripciones()).withRel("inscripcion"));
    }
}
