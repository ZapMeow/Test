package edutechInnovators.proyect.Controller;

import edutechInnovators.proyect.Model.Profesor;
import edutechInnovators.proyect.Service.ProfesorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/edutechinnovations/api/v1/profesor")
@Tag(name = "Profesores", description = "Peticiones con los profesores")
public class ProfesorController {


    @Autowired
    private ProfesorService profesorService;


    @GetMapping
    @Operation(summary = "Obtener profesores", description = "Obtiene una lista con todos los profesores")
    public ResponseEntity<List<Profesor>> getProfesor() {
        List<Profesor> profesores = profesorService.getProfesores();
        if (profesores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(profesores, HttpStatus.OK);
    }


    @PostMapping
    @Operation(summary = "Crear profesor", description = "Inserta un nuevo profesor con los datos dados")
    public ResponseEntity<Profesor> saveProfesor(@RequestBody Profesor profesor) {
        Profesor newProfesor = profesorService.saveProfesor(profesor);
        return new ResponseEntity<>(newProfesor, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene un profesor", description = "Mediante la id obtiene un profesor especifico")
    public ResponseEntity<Profesor> getProfesorById(@PathVariable Long id) {
        System.out.println("getProfesorById");
        try{
            Profesor profesor = profesorService.getProfesorById(id);
            return ResponseEntity.ok(profesor);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un profesor", description = "Actualiza un profesor mediante la id")
    public ResponseEntity<Profesor> updateProfesor(@PathVariable Long id, @RequestBody Profesor profesor) {
        System.out.println("updateProfesor");
        try{
            Profesor newProfesor = profesorService.getProfesorById(id);
            newProfesor.setId_profesor(profesor.getId_profesor());
            newProfesor.setDv_profesor(profesor.getDv_profesor());
            newProfesor.setPnombre_profesor(profesor.getPnombre_profesor());
            newProfesor.setSnombre_profesor(profesor.getSnombre_profesor());
            newProfesor.setAppaterno_profesor(profesor.getAppaterno_profesor());
            newProfesor.setApmaterno_profesor(profesor.getApmaterno_profesor());
            newProfesor.setCorreo_profesor(profesor.getCorreo_profesor());
            newProfesor.setContrasena_profesor(profesor.getContrasena_profesor());
            newProfesor.setFecha_nacimiento_profesor(profesor.getFecha_nacimiento_profesor());

            profesorService.saveProfesor(newProfesor);

            return ResponseEntity.ok(newProfesor);

        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
