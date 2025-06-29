package edutechInnovators.proyect.Assemblers;

import edutechInnovators.proyect.ControllerV2.CursoControllerV2;
import edutechInnovators.proyect.Model.Curso;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CursoModelAssembler implements RepresentationModelAssembler<Curso, EntityModel<Curso>> {

    @Override
    public EntityModel<Curso> toModel(Curso curso) {
        return EntityModel.of(curso,
                linkTo(methodOn(CursoControllerV2.class).getCursoById(curso.getId_curso())).withSelfRel(),
                linkTo(methodOn(CursoControllerV2.class).getAllCursos()).withRel("cursos"));
    }
}
