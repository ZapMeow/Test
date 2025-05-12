package edutechInnovators.proyect.Service;

import edutechInnovators.proyect.Model.Profesor;
import edutechInnovators.proyect.Repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    public List<Profesor> getProfesores() {
        return profesorRepository.findAll();
    }

    public Profesor getProfesor(long id) {
        return profesorRepository.findById(id).get();
    }

    public Profesor saveProfesor(Profesor p) {
        return profesorRepository.save(p);
    }

    public Profesor updateProfesor(Profesor p) {
        return profesorRepository.save(p);
    }

    public void deleteProfesor(long id) {
        profesorRepository.deleteById(id);
    }


}
