package edutechInnovators.proyect.Service;


import edutechInnovators.proyect.Model.Inscripcion;
import edutechInnovators.proyect.Repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    public List<Inscripcion> findAll() {
        return inscripcionRepository.findAll();
    }

    public Inscripcion findById(long id) {
        return inscripcionRepository.findById(id).get();
    }

    public Inscripcion save(Inscripcion inscripcion) {
        return inscripcionRepository.save(inscripcion);
    }

    public void delete(Inscripcion inscripcion) {
        inscripcionRepository.delete(inscripcion);
    }

}
