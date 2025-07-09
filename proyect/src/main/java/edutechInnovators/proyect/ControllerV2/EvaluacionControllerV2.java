package edutechInnovators.proyect.ControllerV2;

import edutechInnovators.proyect.Assemblers.ClienteModelAssembler;
import edutechInnovators.proyect.Assemblers.EvaluacionModelAssembler;
import edutechInnovators.proyect.Model.Cliente;
import edutechInnovators.proyect.Model.Evaluacion;
import edutechInnovators.proyect.Service.ClienteService;
import edutechInnovators.proyect.Service.EvaluacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Evaluaciones", description = "Peticiones para las evaluaciones")
public class EvaluacionControllerV2 {

    @Autowired
    private EvaluacionService evaluacionService;

    @Autowired
    private EvaluacionModelAssembler evaluacionModelAssembler;



    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener evaluaciones", description = "Obtiene una lista de evaluaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado una lista de evaluaciones",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Evaluacion.class))),
            @ApiResponse(responseCode = "204", description = "La solicitud esta bien pero no se han encontrado datos",
                    content = @Content)
    })
    public CollectionModel<EntityModel<Evaluacion>> getAllEvaluaciones(){

        List<EntityModel<Evaluacion>> evaluaciones = evaluacionService.findAll().stream()
                .map(evaluacionModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(evaluaciones, linkTo(methodOn(EvaluacionControllerV2.class).getAllEvaluaciones()).withSelfRel());

    }

    @Operation(summary = "Obtener evaluacion", description = "Obtiene una evaluacion por la id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado una evaluacion",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Evaluacion.class))),
            @ApiResponse(responseCode = "401", description = "No se inserto la id para la busqueda",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "La id especificada no se encuentra",
                    content = @Content)
    })
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Evaluacion> getEvaluacionById(@PathVariable int id ){

        Evaluacion evaluacion = evaluacionService.findById(id);
        return  evaluacionModelAssembler.toModel(evaluacion);

    }

    @Operation(summary = "Inserta una evaluacion", description = "Guarda una evaluacion con los datos dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se ha creado una nueva evaluacion",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Evaluacion.class))),
            @ApiResponse(responseCode = "400", description = "El formato del body esta mal estructurado",
                    content = @Content)
    })
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Evaluacion>> createEvaluacion(@RequestBody Evaluacion evaluacion){

        Evaluacion newEvaluacion = evaluacionService.save(evaluacion);
        return ResponseEntity
                .created(linkTo(methodOn(EvaluacionControllerV2.class).getEvaluacionById(newEvaluacion.getId_eva())).toUri())
                .body(evaluacionModelAssembler.toModel(newEvaluacion));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar evaluacion", description = "Actualiza una evaluacion por la id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha actualizado una evaluacion",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Evaluacion.class))),
            @ApiResponse(responseCode = "401", description = "El cuerpo tiene un mal formato o no hay id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro la evaluacion para actualizar",
                    content = @Content)
    })
    public ResponseEntity<EntityModel<Evaluacion>> updateEvaluacion(@PathVariable int id, @RequestBody Evaluacion evaluacion){
        System.out.println("updateCliente");
        evaluacion.setId_eva(id);
        Evaluacion updateEvaluacion = evaluacionService.save(evaluacion);
        return ResponseEntity.ok(evaluacionModelAssembler.toModel(updateEvaluacion));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Borrar evaluacion", description = "Borra una evaluacion por la id especificada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Se ha borrado una evaluacion exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No se ha colocado la id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro la evaluacion",
                    content = @Content)
    })
    public ResponseEntity<?> deleteEvaluacion(@PathVariable int id){
        System.out.println("deleteCursos");
        Evaluacion evaluacion = evaluacionService.findById(id);
        evaluacionService.delete(evaluacion);
        return ResponseEntity.noContent().build();
    }
}
