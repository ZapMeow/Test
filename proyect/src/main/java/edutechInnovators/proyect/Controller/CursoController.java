package edutechInnovators.proyect.Controller;

import edutechInnovators.proyect.Model.Cliente;
import edutechInnovators.proyect.Model.Curso;
import edutechInnovators.proyect.Model.Evaluacion;
import edutechInnovators.proyect.Model.Profesor;
import edutechInnovators.proyect.Service.CursoService;
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
@RequestMapping("/edutechinnovations/api/v1/curso")
@Tag(name = "Cursos", description = "Peticiones para las carreras")
public class CursoController {

    /**
     * La anotacion @Autowired permide una instancia de dicho atributo sin necesidad de hacerlo
     * uno mismo
     */
    @Autowired
    private CursoService cursoService;

    /**
     * Esta funcion permite obtener todos los componentes de dicha tabla
     * La anotacion @GetMapping permite recibir peticiones GET desde una pagina con
     * un path especifico
     * ResponseEntity permite una escritura y estructuracion de respuesta en formato HTTP
     * @return el retorno depende de la respuesta final estructurandola a gusto.
     */
    @GetMapping
    @Operation(summary = "Ver cursos", description = "Devuelve una lista con todos los cursos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado una lista de cursos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "204", description = "La solicitud esta bien pero no se han encontrado datos",
                content = @Content)
    })
    public List<Curso> getAllCursos(){
        return cursoService.findAll();
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
    @Operation(summary = "Crear curso", description = "Crea un curso con los datos dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se ha creado un nuevo curso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "400", description = "El formato del body esta mal estructurado",
                    content = @Content)
    })
    public Curso createCurso(@RequestBody Curso curso){
        return cursoService.save(curso);
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
    @Operation(summary = "Obtener curso", description = "Obtiene un curso especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado un curso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "401", description = "No se inserto la id para la busqueda",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "La id especificada no se encuentra",
                    content = @Content)
    })
    public ResponseEntity<Curso> getCursoById(@PathVariable Integer id) {
        System.out.println("getCursoById");
        try{
            Curso curso = cursoService.findById(id);
            return ResponseEntity.ok(curso);
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
    @Operation(summary = "Actualizar curso", description = "Actualizar los datos de un curso especifico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha actualizado un curso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Curso.class))),
            @ApiResponse(responseCode = "401", description = "El cuerpo tiene un mal formato o no hay id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro el curso para actualizar",
                    content = @Content)
    })
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

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar curso", description = "Borra un curso por la id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Se ha borrado un curso exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No se ha colocado la id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro el curso",
                    content = @Content)
    })
    public ResponseEntity<?> deleteCurso(@PathVariable long id){

        try{
            Curso curso = cursoService.findById(id);
            cursoService.delete(curso);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
}
