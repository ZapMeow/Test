package edutechInnovators.proyect.Controller;

import edutechInnovators.proyect.Model.Curso;
import edutechInnovators.proyect.Service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edutechinnovations/api/v1/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;



    @GetMapping
    public List<Curso> getAllCursos(){
        return cursoService.findAll();
    }

    @PostMapping
    public Curso createCurso(@RequestBody Curso curso){
        return cursoService.save(curso);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Curso> getCursoById(@PathVariable Integer id) {
        System.out.println("getCursoById");
        try{
            Curso curso = cursoService.findById(id);
            return ResponseEntity.ok(curso);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Curso> updateCurso(@PathVariable int id, @RequestBody Curso curso){

        try{
            Curso cursoUpdate = cursoService.findById(id);
            cursoUpdate.setId_curso(curso.getId_curso());
            cursoUpdate.setNombre_curso(curso.getNombre_curso());
            cursoUpdate.setFecha_creacion_curso(curso.getFecha_creacion_curso());

            return ResponseEntity.ok(cursoUpdate);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
