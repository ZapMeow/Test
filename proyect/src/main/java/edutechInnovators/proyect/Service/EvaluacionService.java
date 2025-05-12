package edutechInnovators.proyect.Service;

import edutechInnovators.proyect.Model.Evaluacion;
import edutechInnovators.proyect.Repository.EvaluacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EvaluacionService {

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    public List<Evaluacion> findAll() {
        return evaluacionRepository.findAll();
    }

    public Evaluacion findById(int id) {
        return evaluacionRepository.findById(id).get();
    }

    public Evaluacion save(Evaluacion evaluacion) {
        return evaluacionRepository.save(evaluacion);
    }

    public void delete(Evaluacion evaluacion) {
        evaluacionRepository.delete(evaluacion);
    }

}
