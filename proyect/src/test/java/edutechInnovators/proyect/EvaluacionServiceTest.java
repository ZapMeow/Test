package edutechInnovators.proyect;

import edutechInnovators.proyect.Model.Evaluacion;
import edutechInnovators.proyect.Model.Inscripcion;
import edutechInnovators.proyect.Repository.EvaluacionRepository;
import edutechInnovators.proyect.Service.EvaluacionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class EvaluacionServiceTest {

    @Autowired
    private EvaluacionService evaluacionService;

    @MockBean
    private EvaluacionRepository evaluacionRepository;

    @Test
    public void TestAllEvaluacionMethods(){
        testFindAllEvaluaciones();
        testFindEvaluacionById();
        testSaveEvaluacion();
        testDeleteEvaluacionById();
    }

    @Test
    public void testFindAllEvaluaciones() {

        when(evaluacionRepository.findAll()).thenReturn(List.of(new Evaluacion(1, "evaluacion de balatro", 25)));
        List<Evaluacion> evaluacionList = evaluacionRepository.findAll();
        assertNotNull(evaluacionList);
        assertEquals(1,evaluacionList.size());

    }

    @Test
    public void testFindEvaluacionById(){

        int id = 1;
        Evaluacion evaluacion = new Evaluacion(1, "evaluacion de balatro", 25);
        when(evaluacionRepository.findById(id)).thenReturn(Optional.of(evaluacion));
        Evaluacion found = evaluacionService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId_eva());

    }


    @Test
    public void testSaveEvaluacion(){

        Evaluacion evaluacion = new Evaluacion(1, "evaluacion de balatro", 25);
        when(evaluacionRepository.save(evaluacion)).thenReturn(evaluacion);
        Evaluacion saved = evaluacionService.save(evaluacion);
        assertNotNull(saved);
        assertEquals(1,saved.getId_eva());
        assertEquals("evaluacion de balatro", saved.getNombre_eva());
        assertEquals(25, saved.getPonderacion_eva());
    }

    @Test
    public void testDeleteEvaluacionById(){

        long id = 1;
        Evaluacion evaluacion = new Evaluacion(1, "evaluacion de balatro", 25);
        doNothing().when(evaluacionRepository).delete(evaluacion);
        evaluacionService.delete(evaluacion);
        verify(evaluacionRepository, times(1)).delete(evaluacion);

    }


}
