package edutechInnovators.proyect.ControllerV2;

import edutechInnovators.proyect.Assemblers.ClienteModelAssembler;
import edutechInnovators.proyect.Assemblers.EvaluacionModelAssembler;
import edutechInnovators.proyect.Model.Cliente;
import edutechInnovators.proyect.Model.Evaluacion;
import edutechInnovators.proyect.Service.ClienteService;
import edutechInnovators.proyect.Service.EvaluacionService;
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
@RequestMapping("/edutechinnovations/api/v2/evaluacion")
public class EvaluacionControllerV2 {

    @Autowired
    private EvaluacionService evaluacionService;

    @Autowired
    private EvaluacionModelAssembler evaluacionModelAssembler;



    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Evaluacion>> getAllEvaluaciones(){

        List<EntityModel<Evaluacion>> evaluaciones = evaluacionService.findAll().stream()
                .map(evaluacionModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(evaluaciones, linkTo(methodOn(EvaluacionControllerV2.class).getAllEvaluaciones()).withSelfRel());

    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Evaluacion> getEvaluacionById(@PathVariable int id ){

        Evaluacion evaluacion = evaluacionService.findById(id);
        return  evaluacionModelAssembler.toModel(evaluacion);

    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Evaluacion>> createEvaluacion(@RequestBody Evaluacion evaluacion){

        Evaluacion newEvaluacion = evaluacionService.save(evaluacion);
        return ResponseEntity
                .created(linkTo(methodOn(EvaluacionControllerV2.class).getEvaluacionById(newEvaluacion.getId_eva())).toUri())
                .body(evaluacionModelAssembler.toModel(newEvaluacion));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Evaluacion>> updateEvaluacion(@PathVariable int id, @RequestBody Evaluacion evaluacion){
        System.out.println("updateCliente");
        evaluacion.setId_eva(id);
        Evaluacion updateEvaluacion = evaluacionService.save(evaluacion);
        return ResponseEntity.ok(evaluacionModelAssembler.toModel(updateEvaluacion));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteEvaluacion(@PathVariable int id){
        System.out.println("deleteCursos");
        Evaluacion evaluacion = evaluacionService.findById(id);
        evaluacionService.delete(evaluacion);
        return ResponseEntity.noContent().build();
    }
}
