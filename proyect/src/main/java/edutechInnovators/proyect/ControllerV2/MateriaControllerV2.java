package edutechInnovators.proyect.ControllerV2;


import edutechInnovators.proyect.Assemblers.MateriaModelAssembler;
import edutechInnovators.proyect.Model.Materia;
import edutechInnovators.proyect.Service.MateriaService;
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
public class MateriaControllerV2 {

    @Autowired
    private MateriaService materiaService;
    @Autowired
    private  MateriaModelAssembler materiaModelAssembler;




    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Materia>> getAllMaterias(){
        System.out.println("getAllMateria");
        List<EntityModel<Materia>>materia = materiaService.getAllMaterias().stream()
                .map(materiaModelAssembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(materia, linkTo(methodOn(MateriaControllerV2.class).getClass()).withSelfRel());
    }

    @PostMapping(value = "/{id}",produces =  MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Materia> getMateriaById(@PathVariable long id){
        System.out.println("createMateria");
        Materia materia =materiaService.getMateriaById(id);
        return materiaModelAssembler.toModel(materia);
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Materia>> createMateria(@RequestBody Materia materia){
        System.out.println("createMateria");
        Materia newMateria = materiaService.save(materia);
        return ResponseEntity
                .created(linkTo(methodOn(MateriaControllerV2.class).getMateriaById(newMateria.getId())).toUri())
                .body(materiaModelAssembler.toModel(newMateria));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Materia>> updateMateria(@PathVariable int id, @RequestBody Materia materia){
        System.out.println("updateMaterial");
        materia.setId(id);
        Materia updateMateria = materiaService.save(materia);
        return ResponseEntity.ok(materiaModelAssembler.toModel(updateMateria));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> deleteMateia(@PathVariable int id){
        System.out.println("deleteMateria");
        Materia materia = materiaService.getMateriaById(id);
        materiaService.delete(materia);
        return ResponseEntity.noContent().build();
    }


}