package edutechInnovators.proyect.Service;

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

    private List<Material> findAll() {
        return materialRepository.findAll();
    }

    private Material findById(long id) {
        return materialRepository.findById(id).get();
    }

    private Material update(Material material) {
        return materialRepository.save(material);
    }

    private void delete(Material material) {
        materialRepository.delete(material);
    }


}
