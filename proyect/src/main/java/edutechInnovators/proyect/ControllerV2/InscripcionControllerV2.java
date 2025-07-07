package edutechInnovators.proyect.ControllerV2;


import edutechInnovators.proyect.Assemblers.EvaluacionModelAssembler;
import edutechInnovators.proyect.Assemblers.InscripcionModelAssembler;
import edutechInnovators.proyect.Model.Evaluacion;
import edutechInnovators.proyect.Model.Inscripcion;
import edutechInnovators.proyect.Service.EvaluacionService;
import edutechInnovators.proyect.Service.InscripcionService;
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
@RequestMapping("/edutechinnovations/api/v2/inscripcion")
public class InscripcionControllerV2 {

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private InscripcionModelAssembler inscripcionModelAssembler;



    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Inscripcion>> getAllInscripciones(){

        List<EntityModel<Inscripcion>> inscripciones = inscripcionService.findAll().stream()
                .map(inscripcionModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(inscripciones, linkTo(methodOn(InscripcionControllerV2.class).getAllInscripciones()).withSelfRel());

    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Inscripcion> getInscripcionById(@PathVariable int id ){

        Inscripcion inscripcion = inscripcionService.findById(id);
        return  inscripcionModelAssembler.toModel(inscripcion);

    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Inscripcion>> createInscripcion(@RequestBody Inscripcion inscripcion){

        Inscripcion newInscripcion = inscripcionService.save(inscripcion);
        return ResponseEntity
                .created(linkTo(methodOn(InscripcionControllerV2.class).getInscripcionById(newInscripcion.getId_ins())).toUri())
                .body(inscripcionModelAssembler.toModel(newInscripcion));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Inscripcion>> updateInscripcion(@PathVariable int id, @RequestBody Inscripcion inscripcion){

        inscripcion.setId_ins(id);
        Inscripcion updateInscripcion = inscripcionService.save(inscripcion);
        return ResponseEntity.ok(inscripcionModelAssembler.toModel(updateInscripcion));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteInscripcion(@PathVariable int id){

        Inscripcion inscripcion = inscripcionService.findById(id);
        inscripcionService.delete(inscripcion);
        return ResponseEntity.noContent().build();
    }
}
