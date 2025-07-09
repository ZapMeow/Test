package edutechInnovators.proyect.Controller;

import edutechInnovators.proyect.Model.*;
import edutechInnovators.proyect.Service.MateriaService;
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
@RequestMapping("/edutechinnovations/api/v1/materia")
@Tag(name = "Materias", description = "Peticiones para las materias")
public class MateriaController {

    /**
     * La anotacion @Autowired permide una instancia de dicho atributo sin necesidad de hacerlo
     * uno mismo
     */
    @Autowired
    private MateriaService materiaService;

    /**
     * Esta funcion permite obtener todos los componentes de dicha tabla
     * La anotacion @GetMapping permite recibir peticiones GET desde una pagina con
     * un path especifico
     * ResponseEntity permite una escritura y estructuracion de respuesta en formato HTTP
     * @return el retorno depende de la respuesta final estructurandola a gusto.
     */
    @GetMapping
    @Operation(summary = "Obtener materias", description = "Obtiene una lista con todas las materias")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado una lista de materias",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Materia.class))),
            @ApiResponse(responseCode = "204", description = "La solicitud esta bien pero no se han encontrado datos",
                content = @Content)
    })
    public List<Materia> getAllMaterias() {
        return materiaService.getAllMaterias();
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
    @Operation(summary = "Agregar una materia", description = "Inserta una materia con los datos especificados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se ha creado una nueva materia",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Materia.class))),
            @ApiResponse(responseCode = "400", description = "El formato del body esta mal estructurado",
                    content = @Content)
    })
    public Materia createMateria(@RequestBody Materia materia) {
        return materiaService.save(materia);
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
    @Operation(summary = "Obtener materia", description = "Obtiene una materia con una id especifica")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha encontrado una materia",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Materia.class))),
            @ApiResponse(responseCode = "401", description = "No se inserto la id para la busqueda",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "La id especificada no se encuentra",
                    content = @Content)
    })
    public ResponseEntity<Materia> getMateriaById(@PathVariable Integer id) {
        System.out.println("getMateriaById");
        try{
            Materia materia = materiaService.getMateriaById(id);
            return ResponseEntity.ok(materia);
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
    @Operation(summary = "Actualizar materia", description = "Actualiza los datos de una materia por la id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se ha actualizado una materia",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Materia.class))),
            @ApiResponse(responseCode = "401", description = "El cuerpo tiene un mal formato o no hay id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro la materia para actualizar",
                    content = @Content)
    })
    public ResponseEntity<Materia> updateMateria(@PathVariable int id, @RequestBody Materia materia){

        try{
            Materia materiaUpdate = materiaService.getMateriaById(id);
            materiaUpdate.setId(materia.getId());
            materiaUpdate.setNombre_materia(materia.getNombre_materia());
            return ResponseEntity.ok(materiaUpdate);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Borrar materia", description = "Borra una materia por la id especificada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Se ha borrado la materia exitosamente",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No se ha colocado la id",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro la materia",
                    content = @Content)
    })
    public ResponseEntity<?> deleteMateria(@PathVariable int id){

        try{
            Materia materia = materiaService.getMateriaById(id);
            materiaService.delete(materia);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }


}
