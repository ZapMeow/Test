package edutechInnovators.proyect.Controller;

import edutechInnovators.proyect.Model.Curso;
import edutechInnovators.proyect.Model.Materia;
import edutechInnovators.proyect.Service.MateriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/edutechinnovations/api/v1/materia")
@Tag(name = "Materias", description = "Peticiones para las materias")
public class MateriaController {


    @Autowired
    private MateriaService materiaService;



    @GetMapping
    @Operation(summary = "Obtener materias", description = "Obtiene una lista con todas las materias")
    public List<Materia> getAllMaterias() {
        return materiaService.getAllMaterias();
    }


    @PostMapping
    @Operation(summary = "Agregar una materia", description = "Inserta una materia con los datos especificados")
    public Materia createMateria(@RequestBody Materia materia) {
        return materiaService.save(materia);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener materia", description = "Obtiene una materia con una id especifica")
    public ResponseEntity<Materia> getMateriaById(@PathVariable Integer id) {
        System.out.println("getMateriaById");
        try{
            Materia materia = materiaService.getMateriaById(id);
            return ResponseEntity.ok(materia);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("{id}")
    @Operation(summary = "Actualizar materia", description = "Actualiza los datos de una materia por la id")
    public ResponseEntity<Materia> updateMateria(@PathVariable int id, @RequestBody Materia materia){

        try{
            Materia materiaUpdate = materiaService.getMateriaById(id);
            materiaUpdate.setId_materia(materia.getId_materia());
            materiaUpdate.setNombre_materia(materia.getNombre_materia());
            return ResponseEntity.ok(materiaUpdate);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }



}
