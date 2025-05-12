package edutechInnovators.proyect.Service;

import edutechInnovators.proyect.Model.Curso;
import edutechInnovators.proyect.Repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Curso findById(long id) {
        return cursoRepository.findById(id).get();
    }

    public Curso save(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void delete(Curso curso) {
        cursoRepository.delete(curso);
    }

}
