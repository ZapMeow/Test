package edutechInnovators.proyect;

import edutechInnovators.proyect.Model.Inscripcion;
import edutechInnovators.proyect.Model.Materia;
import edutechInnovators.proyect.Repository.InscripcionRepository;
import edutechInnovators.proyect.Service.InscripcionService;
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
public class InscripcionServiceTest {

    @Autowired
    private InscripcionService inscripcionService;

    @MockBean
    private InscripcionRepository inscripcionRepository;

    @Test
    public void TestAllInscripcionMethods(){
        testFindAllInscripcion();
        testDeleteInscripcionById();
        testSaveInscripcion();
        testFindInscripcionById();;
    }

    @Test
    public void testFindAllInscripcion() {

        when(inscripcionRepository.findAll()).thenReturn(List.of(new Inscripcion(1, new Date())));
        List<Inscripcion> inscripcionList = inscripcionRepository.findAll();
        assertNotNull(inscripcionList);
        assertEquals(1,inscripcionList.size());

    }

    @Test
    public void testFindInscripcionById(){

        long id = 1;
        Inscripcion inscripcion = new Inscripcion(1, new Date());
        when(inscripcionRepository.findById(id)).thenReturn(Optional.of(inscripcion));
        Inscripcion found = inscripcionService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId_ins());

    }


    @Test
    public void testSaveInscripcion(){

        Date mainDate = new Date();

        Inscripcion inscripcion = new Inscripcion(1, mainDate);
        when(inscripcionRepository.save(inscripcion)).thenReturn(inscripcion);
        Inscripcion saved = inscripcionService.save(inscripcion);
        assertNotNull(saved);
        assertEquals(1,saved.getId_ins());
        assertEquals(mainDate, saved.getFecha_inscripcion_ins());
    }

    @Test
    public void testDeleteInscripcionById(){

        long id = 1;
        Date mainDate = new Date();
        Inscripcion inscripcion = new Inscripcion(1, mainDate);
        doNothing().when(inscripcionRepository).delete(inscripcion);
        inscripcionService.delete(inscripcion);
        verify(inscripcionRepository, times(1)).delete(inscripcion);

    }


}
