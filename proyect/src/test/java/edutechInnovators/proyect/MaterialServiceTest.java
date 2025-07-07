package edutechInnovators.proyect;

import edutechInnovators.proyect.Model.Material;
import edutechInnovators.proyect.Repository.MaterialRepository;
import edutechInnovators.proyect.Service.MaterialService;
import org.junit.jupiter.api.Test;
import org.mockito.internal.stubbing.answers.DoesNothing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class MaterialServiceTest {

    @Autowired
    private MaterialService materialService;

    @MockBean
    private MaterialRepository materialRepository;

    @Test
    public void TestAllMaterialMethods(){
        testFindAllMateriales();
        testDeleteMaterialById();
        testSaveMaterial();
        testFindMaterialById();
    }

    @Test
    public void testFindAllMateriales() {

        when(materialRepository.findAll()).thenReturn(List.of(new Material(1, "www.playbalatro.com", "jugar balatrito")));
        List<Material> materialList = materialService.findAll();
        assertNotNull(materialList);
        assertEquals(1,materialList.size());

    }

    @Test
    public void testFindMaterialById(){

        long id = 1;
        Material material = new Material(1, "www.playbalatro.com", "jugar balatro");
        when(materialRepository.findById(id)).thenReturn(Optional.of(material));
        Material found = materialService.findById(id);
        assertNotNull(found);
        assertEquals(id, found.getId_material());

    }


    @Test
    public void testSaveMaterial(){

        Material material = new Material(1, "www.playbalatro.com", "jugar balatro");
        when(materialRepository.save(material)).thenReturn(material);
        Material saved = materialService.save(material);
        assertNotNull(saved);
        assertEquals(1,saved.getId_material());
        assertEquals("www.playbalatro.com", saved.getUrl_material());
        assertEquals("jugar balatro", saved.getDescripcion_material());
    }

    @Test
    public void testDeleteMaterialById(){

        long id = 1;
        Material material = new Material(1, "www.playbalatro.com", "jugar balatro");
        doNothing().when(materialRepository).delete(material);
        materialService.delete(material);
        verify(materialRepository, times(1)).delete(material);

    }

}
