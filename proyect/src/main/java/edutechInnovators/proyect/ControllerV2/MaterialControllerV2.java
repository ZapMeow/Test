package edutechInnovators.proyect.ControllerV2;


import edutechInnovators.proyect.Assemblers.MaterialModelAssembler;
import edutechInnovators.proyect.Model.Material;
import edutechInnovators.proyect.Service.MaterialService;
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
public class MaterialControllerV2 {

    @Autowired
     private MaterialService materialService;
    @Autowired
     private  MaterialModelAssembler materialModelAssembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Material>> getAllMaterials(){
        System.out.println("getAllMaterials");
        List<EntityModel<Material>>material = materialService.findAll().stream()
                .map(materialModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(material, linkTo(methodOn(MaterialControllerV2.class).getAllMaterials()).withSelfRel());
    }

    @PostMapping(value = "/{id}",produces =  MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Material> getMaterialById(@PathVariable long id){
        System.out.println("createMaterial");
        Material material =materialService.findById(id);
        return materialModelAssembler.toModel(material);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Material>> createMaterial(@RequestBody Material material){
        System.out.println("createMaterial");
        Material newMaterial = materialService.save(material);
        return ResponseEntity
                .created(linkTo(methodOn(MaterialControllerV2.class).getMaterialById(newMaterial.getId_material())).toUri())
                .body(materialModelAssembler.toModel(newMaterial));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Material>> updateMaterial(@PathVariable int id, @RequestBody Material material){
        System.out.println("updateMaterial");
        material.setId_material(id);
        Material updateMaterial = materialService.save(material);
        return ResponseEntity.ok(materialModelAssembler.toModel(updateMaterial));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteMateial(@PathVariable int id){
        System.out.println("deleteMaterial");
        Material material = materialService.findById(id);
        materialService.delete(material);
        return ResponseEntity.noContent().build();
    }


}
