package edutechInnovators.proyect.ControllerV2;

import edutechInnovators.proyect.Assemblers.CursoModelAssembler;
import edutechInnovators.proyect.Model.Curso;
import edutechInnovators.proyect.Service.CursoService;
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
@RequestMapping("/edutechinnovations/api/v2/cursos")
public class CursoControllerV2 {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoModelAssembler cursoModelAssembler;



    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Curso>> getAllCursos(){
        System.out.println("getAllCursos");
        List<EntityModel<Curso>> cursos = cursoService.findAll().stream()
                .map(cursoModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cursos, linkTo(methodOn(CursoControllerV2.class).getAllCursos()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Curso> getCursoById(@PathVariable long id){
        System.out.println("getCursoById");
        Curso curso = cursoService.findById(id);
        return cursoModelAssembler.toModel(curso);

    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Curso>> createCurso(@RequestBody Curso curso){
        System.out.println("createCurso");
        Curso newCurso = cursoService.save(curso);
        return ResponseEntity
                .created(linkTo(methodOn(CursoControllerV2.class).getCursoById(newCurso.getId_curso())).toUri())
                .body(cursoModelAssembler.toModel(newCurso));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Curso>> updateCurso(@PathVariable int id, @RequestBody Curso curso){
        System.out.println("updateCursos");
        curso.setId_curso(id);
        Curso updateCurso = cursoService.save(curso);
        return ResponseEntity.ok(cursoModelAssembler.toModel(updateCurso));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteCurso(@PathVariable int id){
        System.out.println("deleteCursos");
        Curso curso = cursoService.findById(id);
        cursoService.delete(curso);
        return ResponseEntity.noContent().build();
    }


}
