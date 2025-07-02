package edutechInnovators.proyect.Controller;

import edutechInnovators.proyect.Model.Evaluacion;
import edutechInnovators.proyect.Service.EvaluacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/edutechinnovations/api/v1/evaluacion")
@Tag(name = "Evaluaciones", description = "Peticiones para las evaluaciones")
public class EvaluacionController {


    @Autowired
    EvaluacionService evaluacionService;


    @GetMapping
    @Operation(summary = "Obtener evaluaciones", description = "Obtiene una lista de evaluaciones")
    public ResponseEntity<List<Evaluacion>> getEvaluacion() {
        List<Evaluacion> evaluaciones = evaluacionService.findAll();
        if (evaluaciones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(evaluaciones, HttpStatus.OK);
    }


    @PostMapping
    @Operation(summary = "Inserta una evaluacion", description = "Guarda una evaluacion con los datos dados")
    public ResponseEntity<Evaluacion> saveEvaluacion(@RequestBody Evaluacion evaluacion) {
        Evaluacion savedEvaluacion = evaluacionService.save(evaluacion);
        return new ResponseEntity<>(savedEvaluacion, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    @Operation(summary = "Obtener evaluacion", description = "Obtiene una evaluacion por la id")
    public ResponseEntity<Evaluacion> getEvaluacionById(@PathVariable int id) {
        try {
            Evaluacion evaluacion = evaluacionService.findById(id);
            return new ResponseEntity<>(evaluacion, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    @Operation(summary = "Actualizar evaluacion", description = "Actualiza una evaluacion por la id")
    public ResponseEntity<Evaluacion> updateEvaluacion(@PathVariable int id, @RequestBody Evaluacion evaluacion) {

        try{
            Evaluacion newEvaluacion = evaluacionService.findById(id);
            newEvaluacion.setId_eva(evaluacion.getId_eva());
            newEvaluacion.setNombre_eva(evaluacion.getNombre_eva());
            newEvaluacion.setPonderacion_eva(evaluacion.getPonderacion_eva());

            evaluacionService.save(newEvaluacion);

            return new ResponseEntity<>(evaluacionService.save(newEvaluacion), HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
