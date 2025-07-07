package edutechInnovators.proyect;

import edutechInnovators.proyect.Model.Materia;
import edutechInnovators.proyect.Model.Material;
import edutechInnovators.proyect.Repository.MateriaRepository;
import edutechInnovators.proyect.Service.MateriaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
public class MateriaServiceTest {

    @Autowired
    private MateriaService materiaService;

    @MockBean
    private MateriaRepository materiaRepository;

    @Test
    public void TestAllMateriaMethods(){
        testFindAllMaterias();
        testDeleteMateriaById();
        testSaveMateria();
        testFindMateriaById();
    }

    @Test
    public void testFindAllMaterias() {

        when(materiaRepository.findAll()).thenReturn(List.of(new Materia(1, "Aprender a balatrear")));
        List<Materia> materiaList = materiaRepository.findAll();
        assertNotNull(materiaList);
        assertEquals(1,materiaList.size());

    }

    @Test
    public void testFindMateriaById(){

        long id = 1;
        Materia materia = new Materia(1, "Aprender a balatrear");
        when(materiaRepository.findById(id)).thenReturn(Optional.of(materia));
        Materia found = materiaService.getMateriaById(id);
        assertNotNull(found);
        assertEquals(id, found.getId());

    }


    @Test
    public void testSaveMateria(){

        Materia materia = new Materia(1, "Aprender a balatrear");
        when(materiaRepository.save(materia)).thenReturn(materia);
        Materia saved = materiaService.save(materia);
        assertNotNull(saved);
        assertEquals(1,saved.getId());
        assertEquals("Aprender a balatrear", saved.getNombre_materia());
    }

    @Test
    public void testDeleteMateriaById(){

        long id = 1;
        Materia materia = new Materia(1, "Aprender a balatrear");
        doNothing().when(materiaRepository).delete(materia);
        materiaService.delete(materia);
        verify(materiaRepository, times(1)).delete(materia);

    }




}
