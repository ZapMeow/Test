package edutechInnovators.proyect.Service;

import edutechInnovators.proyect.Model.Materia;
import edutechInnovators.proyect.Repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    public List<Materia> getAllMaterias() {
        return materiaRepository.findAll();
    }

    public Materia getMateriaById(long id) {
        return materiaRepository.findById(id).get();
    }

    public void save(Materia materia) {
        materiaRepository.save(materia);
    }

    public void delete(Materia materia) {
        materiaRepository.delete(materia);
    }


}
