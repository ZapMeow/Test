package edutechInnovators.proyect;

import edutechInnovators.proyect.Model.Curso;
import edutechInnovators.proyect.Model.Evaluacion;
import edutechInnovators.proyect.Repository.CursoRepository;
import edutechInnovators.proyect.Service.CursoService;
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
public class CursoServiceTest {

    @Autowired
    private CursoService cursoService;

    @MockBean
    private CursoRepository cursoRepository;

    @Test
    public void TestAllCursoMethods(){
        testFindAllCursos();
        testFindCursoById();
        testSaveCurso();
        testDeleteCursoById();
    }

    @Test
    public void testFindAllCursos() {

        when(cursoRepository.findAll()).thenReturn(List.of(new Curso(1, "balatro university", new Date())));
        List<Curso> cursoList = cursoRepository.findAll();
        assertNotNull(cursoList);
        assertEquals(1,cursoList.size());

    }

    @Test
    public void testFindCursoById(){

        long id = 1;
        Date currentDate = new Date();
        Curso curso = new Curso(1, "balatro university", currentDate);
        when(cursoRepository.findById(id)).thenReturn(Optional.of(curso));
        Curso found = cursoService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId_curso());

    }


    @Test
    public void testSaveCurso(){

        Date currentDate = new Date();

        Curso curso = new Curso(1, "balatro university", currentDate);
        when(cursoRepository.save(curso)).thenReturn(curso);
        Curso saved = cursoService.save(curso);
        assertNotNull(saved);
        assertEquals(1,saved.getId_curso());
        assertEquals("balatro university", saved.getNombre_curso());
        assertEquals(currentDate, saved.getFecha_creacion_curso());
    }

    @Test
    public void testDeleteCursoById(){

        long id = 1;
        Date currentDate = new Date();
        Curso curso = new Curso(1, "balatro university", currentDate);
        doNothing().when(cursoRepository).delete(curso);
        cursoService.delete(curso);
        verify(cursoRepository, times(1)).delete(curso);

    }
}
