package edutechInnovators.proyect.Controller;

import edutechInnovators.proyect.Model.Material;
import edutechInnovators.proyect.Model.Profesor;
import edutechInnovators.proyect.Service.ProfesorService;
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
@RequestMapping("/edutechinnovations/api/v1/profesor")
@Tag(name = "Profesores", description = "Peticiones con los profesores")
public class ProfesorController {

    /**
     * La anotacion @Autowired permide una instancia de dicho atributo sin necesidad de hacerlo
     * uno mismo
     */
    @Autowired
    private ProfesorService profesorService;

    /**
     * Esta funcion permite obtener todos los componentes de dicha tabla
     * La anotacion @GetMapping permite recibir peticiones GET desde una pagina con
     * un path especifico
     * ResponseEntity permite una escritura y estructuracion de respuesta en formato HTTP
     * @return el retorno depende de la respuesta final estructurandola a gusto.
     */
    @GetMapping
    @Operation(summary = "Obtener profesores", description = "Obtiene una lista con todos los profesores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado una lista de profesores",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Profesor.class))),
            @ApiResponse(responseCode = "204", description = "La solicitud esta bien pero no se han encontrado datos",
                content = @Content)
    })
    public ResponseEntity<List<Profesor>> getProfesor() {
        List<Profesor> profesores = profesorService.getProfesores();
        if (profesores.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(profesores, HttpStatus.OK);
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
    @Operation(summary = "Crear profesor", description = "Inserta un nuevo profesor con los datos dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se ha creado un nuevo profesor",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Profesor.class))),
            @ApiResponse(responseCode = "400", description = "El formato del body esta mal estructurado",
                content = @Content)
    })
    public ResponseEntity<Profesor> saveProfesor(@RequestBody Profesor profesor) {
        Profesor newProfesor = profesorService.saveProfesor(profesor);
        return new ResponseEntity<>(newProfesor, HttpStatus.CREATED);
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
    @Operation(summary = "Obtiene un profesor", description = "Mediante la id obtiene un profesor especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado un profesor",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Profesor.class))),
            @ApiResponse(responseCode = "401", description = "No se inserto la id para la busqueda",
                content = @Content),
            @ApiResponse(responseCode = "404", description = "La id especificada no se encuentra",
                content = @Content)
    })
    public ResponseEntity<Profesor> getProfesorById(@PathVariable Long id) {
        System.out.println("getProfesorById");
        try{
            Profesor profesor = profesorService.getProfesorById(id);
            return ResponseEntity.ok(profesor);
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
    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un profesor", description = "Actualiza un profesor mediante la id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha actualizado un profesor",
                content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Profesor.class))),
            @ApiResponse(responseCode = "401", description = "El cuerpo tiene un mal formato o no hay id",
                content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro el profesor a actualizar",
                content = @Content)
    })
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

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar profesor", description = "Borra un profesor por la id especificada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Se ha borrado un profesor exitosamente",
                content = @Content),
            @ApiResponse(responseCode = "401", description = "No se ha colocado la id",
                content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro el profesor",
                content = @Content)
    })
    public ResponseEntity<?> deleteProfesor(@PathVariable long id){

        try{
            Profesor profesor = profesorService.getProfesorById(id);
            profesorService.delete(profesor);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
}
