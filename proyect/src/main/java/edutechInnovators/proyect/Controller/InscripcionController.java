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

/**
 * Esta clase es la encargada de las peticiones REST y el propio funcionamiento de estas
 *
 * La anotacion @RestController indica que esta clase va a actuar como controlador de las
 * peticiones REST
 * La anotacion @RequestMapping indica en que ruta estara dichos servicios para su interaccion con
 * las peticiones REST o tambien endpoint
 */
@RestController
@RequestMapping("/edutechinnovations/api/v1/inscripcion")
@Tag(name = "Inscripciones", description = "Peticiones para las inscripciones")
public class InscripcionController {

    /**
     * La anotacion @Autowired permide una instancia de dicho atributo sin necesidad de hacerlo
     * uno mismo
     */
    @Autowired
    private InscripcionService inscripcionService;

    /**
     * Esta funcion permite obtener todos los componentes de dicha tabla
     * La anotacion @GetMapping permite recibir peticiones GET desde una pagina con
     * un path especifico
     * ResponseEntity permite una escritura y estructuracion de respuesta en formato HTTP
     * @return el retorno depende de la respuesta final estructurandola a gusto.
     */
    @GetMapping
    @Operation(summary = "Obtener inscripciones", description = "Obtiene una lista con las inscripciones")

    public List<Inscripcion> getAllinscripciones() {
        return inscripcionService.findAll();
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
    @Operation(summary = "Crea una inscripcion", description = "Inserta una descripcion con los datos especificados")
    public Inscripcion createInscripcion(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.save(inscripcion);
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

    /**
     * Esta funcion permite actualizar dicho dato especificado
     * La anotacion @PutMapping permite recibir peticiones PUT desde una pagina con
     * un path especifico
     * @PathVariable permite rescatar una variable desde lo que esta escrito en la ruta
     * @RequestBody permite rescatar el body de la peticion
     * ResponseEntity permite una escritura y estructuracion de respuesta en formato HTTP
     * @return el retorno depende de la respuesta final estructurandola a gusto.
     */
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
