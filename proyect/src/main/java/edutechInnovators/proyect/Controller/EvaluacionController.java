package edutechInnovators.proyect.Controller;

import edutechInnovators.proyect.Model.Evaluacion;
import edutechInnovators.proyect.Service.EvaluacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Esta clase es la encargada de las peticiones REST y el propio funcionamiento de estas
 *
 * La anotacion @RestController indica que esta clase va a actuar como controlador de las
 * peticiones REST
 * La anotacion @RequestMapping indica en que ruta estara dichos servicios para su interaccion con
 * las peticiones REST o tambien endpoint
 */
@RestController
@RequestMapping("/edutechinnovations/api/v1/evaluacion")
public class EvaluacionController {

    /**
     * La anotacion @Autowired permide una instancia de dicho atributo sin necesidad de hacerlo
     * uno mismo
     */
    @Autowired
    EvaluacionService evaluacionService;

    /**
     * Esta funcion permite obtener todos los componentes de dicha tabla
     * La anotacion @GetMapping permite recibir peticiones GET desde una pagina con
     * un path especifico
     * ResponseEntity permite una escritura y estructuracion de respuesta en formato HTTP
     * @return el retorno depende de la respuesta final estructurandola a gusto.
     */
    @GetMapping
    public ResponseEntity<List<Evaluacion>> getEvaluacion() {
        List<Evaluacion> evaluaciones = evaluacionService.findAll();
        if (evaluaciones.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(evaluaciones, HttpStatus.OK);
    }

    /**
     * Esta funcin permite insertar datos dependiendo lo especificado
     * La anotacion @PostMapping permite recibir peticiones POST desde una pagina con
     * un path especifico
     * @RequestBody permite rescatar el body de la peticion
     * ResponseEntity permite una escritura y estructuracion de respuesta en formato HTTP
     * @return el retorno depende de la respuesta final estructurandola a gusto.
     */
    @PostMapping
    public ResponseEntity<Evaluacion> saveEvaluacion(@RequestBody Evaluacion evaluacion) {
        Evaluacion savedEvaluacion = evaluacionService.save(evaluacion);
        return new ResponseEntity<>(savedEvaluacion, HttpStatus.CREATED);
    }

    /**
     * Esta funcion permite obtener un dato con una variable especifica
     * La anotacion @GetMapping permite recibir peticiones GET desde una pagina con
     * un path especifico
     * @PathVariable permite rescatar una variable desde lo que esta escrito en la ruta
     * ResponseEntity permite una escritura y estructuracion de respuesta en formato HTTP
     * @return el retorno depende de la respuesta final estructurandola a gusto.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Evaluacion> getEvaluacionById(@PathVariable int id) {
        try {
            Evaluacion evaluacion = evaluacionService.findById(id);
            return new ResponseEntity<>(evaluacion, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Esta funcion permite actualizar dicho dato especificado
     * La anotacion @PutMapping permite recibir peticiones PUT desde una pagina con
     * un path especifico
     * @PathVariable permite rescatar una variable desde lo que esta escrito en la ruta
     * @RequestBody permite rescatar el body de la peticion
     * ResponseEntity permite una escritura y estructuracion de respuesta en formato HTTP
     * @return el retorno depende de la respuesta final estructurandola a gusto.
     */
    @PutMapping("/{id}")
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
