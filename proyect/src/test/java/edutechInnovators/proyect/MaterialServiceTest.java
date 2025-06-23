package edutechInnovators.proyect;

import edutechInnovators.proyect.Model.Material;
import edutechInnovators.proyect.Repository.MaterialRepository;
import edutechInnovators.proyect.Service.MaterialService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MaterialServiceTest {

    @Autowired
    private MaterialService materialService;

    @MockBean
    private MaterialRepository materialRepository;

    @Test
    public void testFindAll() {

        when(materialRepository.findAll()).thenReturn(List.of(new Material(1, "www.playbalatro.com", "jugar balatrito")));

        List<Material> materialList = materialService.findAll();

        assertNotNull(materialList);
        assertEquals(1,materialList.size());

    }

}
