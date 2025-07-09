package edutechInnovators.proyect.ControllerV2;


import edutechInnovators.proyect.Assemblers.EvaluacionModelAssembler;
import edutechInnovators.proyect.Assemblers.InscripcionModelAssembler;
import edutechInnovators.proyect.Model.Evaluacion;
import edutechInnovators.proyect.Model.Inscripcion;
import edutechInnovators.proyect.Service.EvaluacionService;
import edutechInnovators.proyect.Service.InscripcionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Inscripciones", description = "Peticiones para las inscripciones")
public class InscripcionControllerV2 {

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private InscripcionModelAssembler inscripcionModelAssembler;



    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener inscripciones", description = "Obtiene una lista con las inscripciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado una lista de inscripciones",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inscripcion.class))),
            @ApiResponse(responseCode = "204", description = "La solicitud esta bien pero no se han encontrado datos",
                    content = @Content)
    })
    public CollectionModel<EntityModel<Inscripcion>> getAllInscripciones(){

        List<EntityModel<Inscripcion>> inscripciones = inscripcionService.findAll().stream()
                .map(inscripcionModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(inscripciones, linkTo(methodOn(InscripcionControllerV2.class).getAllInscripciones()).withSelfRel());

    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtiene una inscripcion", description = "Obtiene una inscripcion especifica por una id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado una inscripcion",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inscripcion.class))),
            @ApiResponse(responseCode = "401", description = "No se inserto la id para la busqueda",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "La id especificada no se encuentra",
                    content = @Content)
    })
    public EntityModel<Inscripcion> getInscripcionById(@PathVariable int id ){

        Inscripcion inscripcion = inscripcionService.findById(id);
        return  inscripcionModelAssembler.toModel(inscripcion);

    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Crea una inscripcion", description = "Inserta una descripcion con los datos especificados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se ha creado una nueva inscripcion",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inscripcion.class))),
            @ApiResponse(responseCode = "400", description = "El formato del body esta mal estructurado",
                    content = @Content)
    })
    public ResponseEntity<EntityModel<Inscripcion>> createInscripcion(@RequestBody Inscripcion inscripcion){

        Inscripcion newInscripcion = inscripcionService.save(inscripcion);
        return ResponseEntity
                .created(linkTo(methodOn(InscripcionControllerV2.class).getInscripcionById(newInscripcion.getId_ins())).toUri())
                .body(inscripcionModelAssembler.toModel(newInscripcion));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar inscripcion", description = "Actualiza una inscripcion mediante una id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha actualizado una inscripcion",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Inscripcion.class))),
            @ApiResponse(responseCode = "401", description = "El cuerpo tiene un mal formato o no hay id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro la inscripcion para actualizar",
                    content = @Content)
    })
    public ResponseEntity<EntityModel<Inscripcion>> updateInscripcion(@PathVariable int id, @RequestBody Inscripcion inscripcion){

        inscripcion.setId_ins(id);
        Inscripcion updateInscripcion = inscripcionService.save(inscripcion);
        return ResponseEntity.ok(inscripcionModelAssembler.toModel(updateInscripcion));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Borrar inscripcion", description = "Borra una inscripcion por la id especificada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Se ha borrado una inscripcion exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No se ha colocado la id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro la inscripcion",
                    content = @Content)
    })
    public ResponseEntity<?> deleteInscripcion(@PathVariable int id){

        Inscripcion inscripcion = inscripcionService.findById(id);
        inscripcionService.delete(inscripcion);
        return ResponseEntity.noContent().build();
    }
}
