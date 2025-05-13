package edutechInnovators.proyect.Controller;

import edutechInnovators.proyect.Model.Curso;
import edutechInnovators.proyect.Model.Materia;
import edutechInnovators.proyect.Service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edutechinnovations/api/v1/materia")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;


    @GetMapping
    public List<Materia> getAllMaterias() {
        return materiaService.getAllMaterias();
    }

    @PostMapping
    public Materia createMateria(@RequestBody Materia materia) {
        return materiaService.save(materia);
    }

    @GetMapping("/{id}")
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
    public ResponseEntity<Materia> updateMateria(@PathVariable int id, @RequestBody Materia materia){

        try{
            Materia materiaUpdate = materiaService.getMateriaById(id);
            materiaUpdate.setId(materia.getId());
            materiaUpdate.setNombre_materia(materia.getNombre_materia());
            return ResponseEntity.ok(materiaUpdate);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }



}
