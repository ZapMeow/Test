package edutechInnovators.proyect.Controller;

import edutechInnovators.proyect.Model.Inscripcion;
import edutechInnovators.proyect.Service.InscripcionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/edutechinnovations/api/v1/inscripcion")
@Tag(name = "Inscripciones", description = "Peticiones para las inscripciones")
public class InscripcionController {


    @Autowired
    private InscripcionService inscripcionService;


    @GetMapping
    @Operation(summary = "Obtener inscripciones", description = "Obtiene una lista con las inscripciones")
    public List<Inscripcion> getAllinscripciones() {
        return inscripcionService.findAll();
    }


    @PostMapping
    @Operation(summary = "Crea una inscripcion", description = "Inserta una descripcion con los datos especificados")
    public Inscripcion createInscripcion(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.save(inscripcion);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtiene una inscripcion", description = "Obtiene una inscripcion especifica por una id")
    public ResponseEntity<Inscripcion> getInscripcionById(@PathVariable long id) {
        System.out.println("getInscripcionById");
        try{
            Inscripcion inscripcion = inscripcionService.findById(id);
            return ResponseEntity.ok(inscripcion);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("{id}")
    @Operation(summary = "Actualizar inscripcion", description = "Actualiza una inscripcion mediante una id")
    public ResponseEntity<Inscripcion> updateinscripcion(@PathVariable long id, @RequestBody Inscripcion inscripcion) {

        try{
            Inscripcion inscripcionUpdate = inscripcionService.findById(id);
            inscripcionUpdate.setId_ins(inscripcion.getId_ins());
            inscripcionUpdate.setFecha_inscripcion_ins(inscripcion.getFecha_inscripcion_ins());

            return ResponseEntity.ok(inscripcionUpdate);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }





}
