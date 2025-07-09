package edutechInnovators.proyect.ControllerV2;

import edutechInnovators.proyect.Assemblers.EvaluacionModelAssembler;
import edutechInnovators.proyect.Assemblers.ProfesorModelAssembler;
import edutechInnovators.proyect.Model.Evaluacion;
import edutechInnovators.proyect.Model.Profesor;
import edutechInnovators.proyect.Service.EvaluacionService;
import edutechInnovators.proyect.Service.ProfesorService;
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
@RequestMapping("/edutechinnovations/api/v2/profesor")
@Tag(name = "Profesores", description = "Peticiones para los profesores")
public class ProfesorControllerV2 {

    @Autowired
    private ProfesorService profesorService;

    @Autowired
    private ProfesorModelAssembler profesorModelAssembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener profesores", description = "Obtiene una lista con todos los profesores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado una lista de profesores",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profesor.class))),
            @ApiResponse(responseCode = "204", description = "La solicitud esta bien pero no se han encontrado datos",
                    content = @Content)
    })
    public CollectionModel<EntityModel<Profesor>> getAllProfesores(){

        List<EntityModel<Profesor>> profesores = profesorService.getProfesores().stream()
                .map(profesorModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(profesores, linkTo(methodOn(ProfesorControllerV2.class).getAllProfesores()).withSelfRel());

    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtiene un profesor", description = "Mediante la id obtiene un profesor especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado un profesor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profesor.class))),
            @ApiResponse(responseCode = "401", description = "No se inserto la id para la busqueda",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "La id especificada no se encuentra",
                    content = @Content)
    })
    public EntityModel<Profesor> getProfesorById(@PathVariable int id ){

        Profesor profesor = profesorService.getProfesorById(id);
        return  profesorModelAssembler.toModel(profesor);

    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Crear profesor", description = "Inserta un nuevo profesor con los datos dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se ha creado un nuevo profesor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profesor.class))),
            @ApiResponse(responseCode = "400", description = "El formato del body esta mal estructurado",
                    content = @Content)
    })
    public ResponseEntity<EntityModel<Profesor>> createProfesor(@RequestBody Profesor profesor){

        Profesor newProfesor = profesorService.saveProfesor(profesor);
        return ResponseEntity
                .created(linkTo(methodOn(ProfesorControllerV2.class).getProfesorById(newProfesor.getId_profesor())).toUri())
                .body(profesorModelAssembler.toModel(newProfesor));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualiza un profesor", description = "Actualiza un profesor mediante la id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha actualizado un profesor",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profesor.class))),
            @ApiResponse(responseCode = "401", description = "El cuerpo tiene un mal formato o no hay id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro el profesor a actualizar",
                    content = @Content)
    })
    public ResponseEntity<EntityModel<Profesor>> updateProfesor(@PathVariable int id, @RequestBody Profesor profesor){

        profesor.setId_profesor(id);
        Profesor updateProfesor = profesorService.saveProfesor(profesor);
        return ResponseEntity.ok(profesorModelAssembler.toModel(updateProfesor));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Borrar profesor", description = "Borra un profesor por la id especificada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Se ha borrado un profesor exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No se ha colocado la id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro el profesor",
                    content = @Content)
    })
    public ResponseEntity<?> deleteProfesor(@PathVariable int id){

        Profesor profesor = profesorService.getProfesorById(id);
        profesorService.delete(profesor);
        return ResponseEntity.noContent().build();
    }
}
