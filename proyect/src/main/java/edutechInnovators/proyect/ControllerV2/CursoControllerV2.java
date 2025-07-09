package edutechInnovators.proyect.ControllerV2;

import edutechInnovators.proyect.Assemblers.CursoModelAssembler;
import edutechInnovators.proyect.Model.Curso;
import edutechInnovators.proyect.Service.CursoService;
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
@RequestMapping("/edutechinnovations/api/v2/curso")
@Tag(name = "Cursos", description = "Peticiones para las carreras")
public class CursoControllerV2 {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoModelAssembler cursoModelAssembler;



    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Ver cursos", description = "Devuelve una lista con todos los cursos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado una lista de cursos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "204", description = "La solicitud esta bien pero no se han encontrado datos",
                    content = @Content)
    })
    public CollectionModel<EntityModel<Curso>> getAllCursos(){
        System.out.println("getAllCursos");
        List<EntityModel<Curso>> cursos = cursoService.findAll().stream()
                .map(cursoModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(cursos, linkTo(methodOn(CursoControllerV2.class).getAllCursos()).withSelfRel());
    }

    @Operation(summary = "Obtener curso", description = "Obtiene un curso especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado un curso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "401", description = "No se inserto la id para la busqueda",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "La id especificada no se encuentra",
                    content = @Content)
    })
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Curso> getCursoById(@PathVariable long id){
        System.out.println("getCursoById");
        Curso curso = cursoService.findById(id);
        return cursoModelAssembler.toModel(curso);

    }

    @Operation(summary = "Crear curso", description = "Crea un curso con los datos dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se ha creado un nuevo curso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "400", description = "El formato del body esta mal estructurado",
                    content = @Content)
    })
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Curso>> createCurso(@RequestBody Curso curso){
        System.out.println("createCurso");
        Curso newCurso = cursoService.save(curso);
        return ResponseEntity
                .created(linkTo(methodOn(CursoControllerV2.class).getCursoById(newCurso.getId_curso())).toUri())
                .body(cursoModelAssembler.toModel(newCurso));
    }

    @Operation(summary = "Actualizar curso", description = "Actualizar los datos de un curso especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha actualizado un curso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "401", description = "El cuerpo tiene un mal formato o no hay id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro el curso para actualizar",
                    content = @Content)
    })

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Curso>> updateCurso(@PathVariable int id, @RequestBody Curso curso){
        System.out.println("updateCursos");
        curso.setId_curso(id);
        Curso updateCurso = cursoService.save(curso);
        return ResponseEntity.ok(cursoModelAssembler.toModel(updateCurso));
    }

    @Operation(summary = "Borrar curso", description = "Borra un curso por la id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Se ha borrado un curso exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No se ha colocado la id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro el curso",
                    content = @Content)
    })
    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteCurso(@PathVariable int id){
        System.out.println("deleteCursos");
        Curso curso = cursoService.findById(id);
        cursoService.delete(curso);
        return ResponseEntity.noContent().build();
    }


}
