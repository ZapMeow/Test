package edutechInnovators.proyect.Controller;

import edutechInnovators.proyect.Model.Cliente;
import edutechInnovators.proyect.Model.Material;
import edutechInnovators.proyect.Service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/edutechinnovations/api/v1/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    @GetMapping
    public ResponseEntity<List<Material>> getMaterials() {
        List<Material> materials = materialService.findAll();
        if (materials.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Material> saveMaterial(@RequestBody Material material) {
        System.out.println("saveMaterial");
        Material newMaterial = materialService.save(material);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMaterial);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Material> getMaterialById(@PathVariable Integer id) {
        System.out.println("getMaterialById");
        try{
            Material material = materialService.findById(id);
            return ResponseEntity.ok(material);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
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
