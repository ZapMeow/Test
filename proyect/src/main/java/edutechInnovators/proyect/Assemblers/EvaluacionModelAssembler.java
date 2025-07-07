package edutechInnovators.proyect.Assemblers;

import edutechInnovators.proyect.ControllerV2.EvaluacionControllerV2;
import edutechInnovators.proyect.ControllerV2.MaterialControllerV2;
import edutechInnovators.proyect.Model.Curso;
import edutechInnovators.proyect.Model.Evaluacion;
import edutechInnovators.proyect.Model.Material;
import jakarta.persistence.Column;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EvaluacionModelAssembler implements RepresentationModelAssembler<Evaluacion, EntityModel<Evaluacion>> {

    @Override
    public EntityModel<Evaluacion> toModel(Evaluacion evaluacion){
        return  EntityModel.of(evaluacion,
                linkTo(methodOn(EvaluacionControllerV2.class).getEvaluacionById(evaluacion.getId_eva())).withSelfRel(),
                linkTo(methodOn(EvaluacionControllerV2.class).getAllEvaluaciones()).withRel("evaluaciones"));
    }
}
