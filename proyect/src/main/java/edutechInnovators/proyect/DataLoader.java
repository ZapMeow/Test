package edutechInnovators.proyect;

import edutechInnovators.proyect.Model.Cliente;
import edutechInnovators.proyect.Repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;


@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private EvaluacionRepository evaluacionRepository;
    @Autowired
    private InscripcionRepository inscripcionRepository;
    @Autowired
    private MaterialRepository materialRepository;
    @Autowired
    private MateriaRepository materiaRepository;
    @Autowired
    private ProfesorRepository profesorRepository;


    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();



        //Cliente
        for (int i = 0; i < 3; i++){
            Cliente newCliente = new Cliente();
            newCliente.setPnombre_cliente(faker.name().firstName());
            newCliente.setSnombre_cliente(faker.name().lastName());
            newCliente.setAppaterno_cliente(faker.clashOfClans().defensiveBuilding());
            newCliente.setApmaterno_cliente(faker.clashOfClans().rank());
            newCliente.setCorreo_cliente(faker.internet().emailAddress());
            newCliente.setContrasena_cliente(faker.internet().password(4,10));
            newCliente.setRun_cliente(faker.number().numberBetween(11111111, 33333333));
            int numberOrK = random.nextInt(2);
            if (numberOrK == 1){
                newCliente.setDv_cliente(String.valueOf(faker.number().numberBetween(0,9)));
            }else{
                newCliente.setDv_cliente("K");
            }
            newCliente.setActivo_cliente(faker.bool().bool());
            newCliente.setFecha_nacimiento_cliente(new Date());

            clienteRepository.save(newCliente);
        }

        List<Cliente> clientes = clienteRepository.findAll();




    }
}
