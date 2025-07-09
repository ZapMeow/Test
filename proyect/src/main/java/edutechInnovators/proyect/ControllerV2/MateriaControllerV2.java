package edutechInnovators.proyect.ControllerV2;


import edutechInnovators.proyect.Assemblers.MateriaModelAssembler;
import edutechInnovators.proyect.Model.Materia;
import edutechInnovators.proyect.Service.MateriaService;
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
@RequestMapping ("/edutechinnovations/api/v2/materia")
@Tag(name = "Materias", description = "Peticiones para las materias")
public class MateriaControllerV2 {

    @Autowired
    private MateriaService materiaService;
    @Autowired
    private  MateriaModelAssembler materiaModelAssembler;




    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener materias", description = "Obtiene una lista con todas las materias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado una lista de materias",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Materia.class))),
            @ApiResponse(responseCode = "204", description = "La solicitud esta bien pero no se han encontrado datos",
                    content = @Content)
    })
    public CollectionModel<EntityModel<Materia>> getAllMaterias(){
        System.out.println("getAllMateria");
        List<EntityModel<Materia>>materia = materiaService.getAllMaterias().stream()
                .map(materiaModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(materia, linkTo(methodOn(MateriaControllerV2.class).getClass()).withSelfRel());
    }

    @GetMapping(value = "/{id}",produces =  MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener materia", description = "Obtiene una materia con una id especifica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado una materia",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Materia.class))),
            @ApiResponse(responseCode = "401", description = "No se inserto la id para la busqueda",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "La id especificada no se encuentra",
                    content = @Content)
    })
    public EntityModel<Materia> getMateriaById(@PathVariable long id){
        System.out.println("createMateria");
        Materia materia =materiaService.getMateriaById(id);
        return materiaModelAssembler.toModel(materia);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Agregar una materia", description = "Inserta una materia con los datos especificados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se ha creado una nueva materia",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Materia.class))),
            @ApiResponse(responseCode = "400", description = "El formato del body esta mal estructurado",
                    content = @Content)
    })
    public ResponseEntity<EntityModel<Materia>> createMateria(@RequestBody Materia materia){
        System.out.println("createMateria");
        Materia newMateria = materiaService.save(materia);
        return ResponseEntity
                .created(linkTo(methodOn(MateriaControllerV2.class).getMateriaById(newMateria.getId())).toUri())
                .body(materiaModelAssembler.toModel(newMateria));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar materia", description = "Actualiza los datos de una materia por la id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha actualizado una materia",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Materia.class))),
            @ApiResponse(responseCode = "401", description = "El cuerpo tiene un mal formato o no hay id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro la materia para actualizar",
                    content = @Content)
    })
    public ResponseEntity<EntityModel<Materia>> updateMateria(@PathVariable int id, @RequestBody Materia materia){
        System.out.println("updateMaterial");
        materia.setId(id);
        Materia updateMateria = materiaService.save(materia);
        return ResponseEntity.ok(materiaModelAssembler.toModel(updateMateria));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Borrar materia", description = "Borra una materia por la id especificada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Se ha borrado la materia exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No se ha colocado la id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro la materia",
                    content = @Content)
    })
    public ResponseEntity<?> deleteMateia(@PathVariable int id){
        System.out.println("deleteMateria");
        Materia materia = materiaService.getMateriaById(id);
        materiaService.delete(materia);
        return ResponseEntity.noContent().build();
    }


}