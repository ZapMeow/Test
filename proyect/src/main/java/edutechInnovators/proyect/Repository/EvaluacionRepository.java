package edutechInnovators.proyect.Repository;

import edutechInnovators.proyect.Model.Evaluacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluacionRepository extends JpaRepository<Evaluacion, Integer> {
}
