package edutechInnovators.proyect;

import edutechInnovators.proyect.Model.Profesor;
import edutechInnovators.proyect.Repository.ProfesorRepository;
import edutechInnovators.proyect.Service.ProfesorService;
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
public class ProfesorServiceTest {

    @Autowired
    private ProfesorService profesorService;

    @MockBean
    private ProfesorRepository profesorRepository;


    @Test
    public void TestAllProfesorMethods(){
        testFindAllProfesores();
        testFindProfesorById();
        testSaveProfesor();
        testDeleteProfesorById();
    }

    @Test
    public void testFindAllProfesores() {

        Date currentDate = new Date();
        when(profesorRepository.findAll()).thenReturn(List.of(new Profesor(1, 21141971, "7", "balatro", "lua", "balatrez", "baraja plasmatica", "balatro@gmail.com", "12345678", currentDate)));
        List<Profesor> profesorList = profesorRepository.findAll();
        assertNotNull(profesorList);
        assertEquals(1,profesorList.size());

    }

    @Test
    public void testFindProfesorById(){

        long id = 1;
        Date currentDate = new Date();
        Profesor profesor = new Profesor(1, 21141971, "7", "balatro", "lua", "balatrez", "baraja plasmatica", "balatro@gmail.com", "12345678", currentDate);
        when(profesorRepository.findById(id)).thenReturn(Optional.of(profesor));
        Profesor found = profesorService.getProfesorById(id);
        assertNotNull(found);
        assertEquals(id, found.getId_profesor());

    }


    @Test
    public void testSaveProfesor(){

        Date currentDate = new Date();

        Profesor profesor = new Profesor(1, 21141971, "7", "balatro", "lua", "balatrez", "baraja plasmatica", "balatro@gmail.com", "12345678", currentDate);
        when(profesorRepository.save(profesor)).thenReturn(profesor);
        Profesor saved = profesorService.saveProfesor(profesor);
        assertNotNull(saved);
        assertEquals(1,saved.getId_profesor());
        assertEquals(21141971, saved.getRun_profesor());
        assertEquals("7", saved.getDv_profesor());
        assertEquals("balatro", saved.getPnombre_profesor());
        assertEquals("lua", saved.getSnombre_profesor());
        assertEquals("balatrez", saved.getAppaterno_profesor());
        assertEquals("baraja plasmatica", saved.getApmaterno_profesor());
        assertEquals("balatro@gmail.com", saved.getCorreo_profesor());
        assertEquals("12345678", saved.getContrasena_profesor());
        assertEquals(currentDate, saved.getFecha_nacimiento_profesor());

    }

    @Test
    public void testDeleteProfesorById(){

        Date currentDate = new Date();
        Profesor profesor = new Profesor(1, 21141971, "7", "balatro", "lua", "balatrez", "baraja plasmatica", "balatro@gmail.com", "12345678", currentDate);
        doNothing().when(profesorRepository).delete(profesor);
        profesorService.delete(profesor);
        verify(profesorRepository, times(1)).delete(profesor);

    }



}
