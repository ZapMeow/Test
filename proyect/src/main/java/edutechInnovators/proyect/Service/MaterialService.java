package edutechInnovators.proyect.Service;

import edutechInnovators.proyect.Model.Materia;
import edutechInnovators.proyect.Model.Material;
import edutechInnovators.proyect.Repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public List<Material> findAll() {
        return materialRepository.findAll();
    }

    public Material save(Material material) {
        materialRepository.save(material);
        return material;
    }

    public Material findById(long id) {
        return materialRepository.findById(id).get();
    }

    public Material update(Material material) {
        return materialRepository.save(material);
    }

    public void delete(Material material) {
        materialRepository.delete(material);
    }


}
