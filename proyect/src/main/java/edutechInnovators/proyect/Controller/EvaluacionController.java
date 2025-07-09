package edutechInnovators.proyect.Controller;

import edutechInnovators.proyect.Model.Curso;
import edutechInnovators.proyect.Model.Evaluacion;
import edutechInnovators.proyect.Model.Profesor;
import edutechInnovators.proyect.Service.EvaluacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Evaluaciones", description = "Peticiones para las evaluaciones")
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
    @Operation(summary = "Obtener evaluaciones", description = "Obtiene una lista de evaluaciones")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado una lista de evaluaciones",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Evaluacion.class))),
            @ApiResponse(responseCode = "204", description = "La solicitud esta bien pero no se han encontrado datos",
                content = @Content)
    })
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
    @Operation(summary = "Inserta una evaluacion", description = "Guarda una evaluacion con los datos dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se ha creado una nueva evaluacion",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Evaluacion.class))),
            @ApiResponse(responseCode = "400", description = "El formato del body esta mal estructurado",
                    content = @Content)
    })
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
    @Operation(summary = "Obtener evaluacion", description = "Obtiene una evaluacion por la id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado una evaluacion",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Evaluacion.class))),
            @ApiResponse(responseCode = "401", description = "No se inserto la id para la busqueda",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "La id especificada no se encuentra",
                    content = @Content)
    })
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
    @Operation(summary = "Actualizar evaluacion", description = "Actualiza una evaluacion por la id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha actualizado una evaluacion",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Evaluacion.class))),
            @ApiResponse(responseCode = "401", description = "El cuerpo tiene un mal formato o no hay id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro la evaluacion para actualizar",
                    content = @Content)
    })
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

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar evaluacion", description = "Borra una evaluacion por la id especificada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Se ha borrado una evaluacion exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No se ha colocado la id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro la evaluacion",
                    content = @Content)
    })
    public ResponseEntity<?> deleteEvaluacion(@PathVariable int id){

        try{
            Evaluacion evaluacion = evaluacionService.findById(id);
            evaluacionService.delete(evaluacion);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }

}
