package edutechInnovators.proyect.Controller;

import edutechInnovators.proyect.Model.Curso;
import edutechInnovators.proyect.Model.Profesor;
import edutechInnovators.proyect.Service.CursoService;
import edutechInnovators.proyect.Service.ProfesorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/edutechinnovations/api/v1/curso")
@Tag(name = "Cursos", description = "Peticiones para las carreras")
public class CursoController {


    @Autowired
    private CursoService cursoService;

    @Autowired
    private ProfesorService profesorService;


    @GetMapping
    @Operation(summary = "Ver cursos", description = "Devuelve una lista con todos los cursos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK - Se obtiene el curso",
                content = @Content(schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "404", description = "Not found - El recurso solicitado no existe",
                content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error - Error del servidor",
                content = @Content)
    })
    public List<Curso> getAllCursos(){
        return cursoService.findAll();
    }


    @PostMapping
    @Operation(summary = "Crear curso", description = "Crea un curso con los datos dados")
    public ResponseEntity<?> createCurso(@RequestBody DTOcurso dtocurso){
        try{
            Profesor currentProfesor = profesorService.getProfesorById(dtocurso.getId_profesor());
            Curso curso = new Curso();
            curso.setNombre_curso(dtocurso.getNombre_curso());
            curso.setFecha_creacion_curso(dtocurso.getFecha_creacion_curso());
            curso.setProfesor(currentProfesor);
            cursoService.save(curso);
            return ResponseEntity.ok().body(Map.of("id", curso.getId_curso(),
                                                   "Nombre_curso", curso.getNombre_curso(),
                                                   "fecha_creacion", curso.getFecha_creacion_curso(),
                                                   "id_profesor", curso.getProfesor().getId_profesor()));
        }catch (Exception e){
            String error = "Error: el profesor con ID " + dtocurso.getId_profesor()
                        + " no existe. No se creó el curso.";
            Curso failedCurso = new Curso(0, "error", new Date(), new Profesor());
            failedCurso.setNombre_curso("invalid");
            return ResponseEntity.badRequest().body(Map.of("error", error));
        }

    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener curso", description = "Obtiene un curso especifico")
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
    @Operation(summary = "Actualizar curso", description = "Actualizar los datos de un curso especifico")
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
