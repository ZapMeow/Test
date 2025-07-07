package edutechInnovators.proyect.ControllerV2;

import edutechInnovators.proyect.Assemblers.EvaluacionModelAssembler;
import edutechInnovators.proyect.Assemblers.ProfesorModelAssembler;
import edutechInnovators.proyect.Model.Evaluacion;
import edutechInnovators.proyect.Model.Profesor;
import edutechInnovators.proyect.Service.EvaluacionService;
import edutechInnovators.proyect.Service.ProfesorService;
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
@RequestMapping("/edutechinnovations/api/v2/profesor")
public class ProfesorControllerV2 {

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private ProfesorModelAssembler profesorModelAssembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Profesor>> getAllProfesores(){

        List<EntityModel<Profesor>> profesores = profesorService.getProfesores().stream()
                .map(profesorModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(profesores, linkTo(methodOn(ProfesorControllerV2.class).getAllProfesores()).withSelfRel());

    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Profesor> getProfesorById(@PathVariable int id ){

        Profesor profesor = profesorService.getProfesorById(id);
        return  profesorModelAssembler.toModel(profesor);

    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Profesor>> createProfesor(@RequestBody Profesor profesor){

        Profesor newProfesor = profesorService.saveProfesor(profesor);
        return ResponseEntity
                .created(linkTo(methodOn(ProfesorControllerV2.class).getProfesorById(newProfesor.getId_profesor())).toUri())
                .body(profesorModelAssembler.toModel(newProfesor));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Profesor>> updateProfesor(@PathVariable int id, @RequestBody Profesor profesor){

        profesor.setId_profesor(id);
        Profesor updateProfesor = profesorService.saveProfesor(profesor);
        return ResponseEntity.ok(profesorModelAssembler.toModel(updateProfesor));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteProfesor(@PathVariable int id){

        Profesor profesor = profesorService.getProfesorById(id);
        profesorService.delete(profesor);
        return ResponseEntity.noContent().build();
    }
}
