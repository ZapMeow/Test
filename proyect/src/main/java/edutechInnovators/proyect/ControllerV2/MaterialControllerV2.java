package edutechInnovators.proyect.ControllerV2;


import edutechInnovators.proyect.Assemblers.MaterialModelAssembler;
import edutechInnovators.proyect.Model.Material;
import edutechInnovators.proyect.Service.MaterialService;
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
@RequestMapping ("/edutechinnovations/api/v2/material")
@Tag(name = "Materiales", description = "Peticiones para los materiales")
public class MaterialControllerV2 {

    @Autowired
     private MaterialService materialService;
    @Autowired
     private  MaterialModelAssembler materialModelAssembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener materiales", description = "Obtiene todos los materiales disponibles")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado una lista de materiales",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Material.class))),
            @ApiResponse(responseCode = "204", description = "La solicitud esta bien pero no se han encontrado datos",
                    content = @Content)
    })
    public CollectionModel<EntityModel<Material>> getAllMaterials(){
        System.out.println("getAllMaterials");
        List<EntityModel<Material>>material = materialService.findAll().stream()
                .map(materialModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(material, linkTo(methodOn(MaterialControllerV2.class).getAllMaterials()).withSelfRel());
    }

    @GetMapping(value = "/{id}",produces =  MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Obtener material", description = "Obtiene un material especifico por la id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado un material",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Material.class))),
            @ApiResponse(responseCode = "401", description = "No se inserto la id para la busqueda",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "La id especificada no se encuentra",
                    content = @Content)
    })
    public EntityModel<Material> getMaterialById(@PathVariable long id){
        System.out.println("createMaterial");
        Material material =materialService.findById(id);
        return materialModelAssembler.toModel(material);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Agregar un material", description = "Inserta un nuevo material con los datos especificados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se ha creado un nuevo material",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Material.class))),
            @ApiResponse(responseCode = "400", description = "El formato del body esta mal estructurado",
                    content = @Content)
    })
    public ResponseEntity<EntityModel<Material>> createMaterial(@RequestBody Material material){
        System.out.println("createMaterial");
        Material newMaterial = materialService.save(material);
        return ResponseEntity
                .created(linkTo(methodOn(MaterialControllerV2.class).getMaterialById(newMaterial.getId_material())).toUri())
                .body(materialModelAssembler.toModel(newMaterial));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Actualizar material", description = "Actualiza un material por la id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha actualizado un material",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Material.class))),
            @ApiResponse(responseCode = "401", description = "El cuerpo tiene un mal formato o no hay id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro el material a actualizar",
                    content = @Content)
    })
    public ResponseEntity<EntityModel<Material>> updateMaterial(@PathVariable int id, @RequestBody Material material){
        System.out.println("updateMaterial");
        material.setId_material(id);
        Material updateMaterial = materialService.save(material);
        return ResponseEntity.ok(materialModelAssembler.toModel(updateMaterial));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    @Operation(summary = "Borrar material", description = "Borra un material por la id especificada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Se ha borrado un material exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No se ha colocado la id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro el material",
                    content = @Content)
    })
    public ResponseEntity<?> deleteMaterial(@PathVariable int id){
        System.out.println("deleteMaterial");
        Material material = materialService.findById(id);
        materialService.delete(material);
        return ResponseEntity.noContent().build();
    }


}
