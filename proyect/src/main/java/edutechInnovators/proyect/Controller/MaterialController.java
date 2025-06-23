package edutechInnovators.proyect.Controller;

import edutechInnovators.proyect.Model.Material;
import edutechInnovators.proyect.Service.MaterialService;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/edutechinnovations/api/v1/material")
@Tag(name = "Materiales", description = "Peticiones con los materiales")
public class MaterialController {

    /**
     * La anotacion @Autowired permide una instancia de dicho atributo sin necesidad de hacerlo
     * uno mismo
     */
    @Autowired
    private MaterialService materialService;

    /**
     * Esta funcion permite obtener todos los componentes de dicha tabla
     * La anotacion @GetMapping permite recibir peticiones GET desde una pagina con
     * un path especifico
     * ResponseEntity permite una escritura y estructuracion de respuesta en formato HTTP
     * @return el retorno depende de la respuesta final estructurandola a gusto.
     */
    @GetMapping
    @Operation(summary = "Obtener materiales", description = "Obtiene todos los materiales disponibles")
    public ResponseEntity<List<Material>> getMaterials() {
        List<Material> materials = materialService.findAll();
        if (materials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(materials, HttpStatus.OK);
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
    @Operation(summary = "Agregar un material", description = "Inserta un nuevo material con los datos especificados")
    public ResponseEntity<Material> saveMaterial(@RequestBody Material material) {
        System.out.println("saveMaterial");
        Material newMaterial = materialService.save(material);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMaterial);
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
    @Operation(summary = "Obtener material", description = "Obtiene un material especifico por la id")
    public ResponseEntity<Material> getMaterialById(@PathVariable Integer id) {
        System.out.println("getMaterialById");
        try{
            Material material = materialService.findById(id);
            return ResponseEntity.ok(material);
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
    @Operation(summary = "Actualizar material", description = "Actualiza un material por la id")
    public ResponseEntity<Material> updateMaterial(@PathVariable Integer id, @RequestBody Material material) {
        System.out.println("updateMaterial");
        try{
            Material newMaterial = materialService.findById(id);
            newMaterial.setId_material(material.getId_material());
            newMaterial.setDescripcion_material(material.getDescripcion_material());
            newMaterial.setUrl_material(material.getUrl_material());

            materialService.save(newMaterial);

            return ResponseEntity.ok(newMaterial);

        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }




}
