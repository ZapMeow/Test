package edutechInnovators.proyect;

import edutechInnovators.proyect.Model.*;
import edutechInnovators.proyect.Repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

//ADVERTENCIA PUEDE GENERAR ERRORES
@Profile("test")
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
        Locale locale = new Locale("es", "CL");
        Faker faker = new Faker(locale);
        Random random = new Random();

        //cuantos datos de cada entidad se quieren
        final int CANTIDAD_DE_CLIENTES = 30;
        final int CANTIDAD_DE_CURSO = 30;
        final int CANTIDAD_DE_EVALUACION = 30;
        final int CANTIDAD_DE_INSCRIPCION = 30;
        final int CANTIDAD_DE_MATERIA = 30;
        final int CANTIDAD_DE_MATERIAL = 30;
        final int CANTIDAD_DE_PROFESOR = 30;

        //GENERAR CLIENTES

        for (int i = 0; i < CANTIDAD_DE_CLIENTES; i++){

            Cliente newCliente = new Cliente();
            //newCliente.setId_cliente(i+1);
            newCliente.setRun_cliente(faker.number().numberBetween(1000000, 30000000));
            newCliente.setDv_cliente(String.valueOf(faker.number().numberBetween(0,9)));
            newCliente.setPnombre_cliente(faker.clashOfClans().defensiveBuilding());
            newCliente.setSnombre_cliente(faker.clashOfClans().troop());
            newCliente.setAppaterno_cliente(faker.minecraft().itemName());
            newCliente.setApmaterno_cliente(faker.onePiece().character());
            newCliente.setCorreo_cliente(faker.internet().emailAddress());
            newCliente.setContrasena_cliente(faker.internet().password(6,10));
            newCliente.setFecha_nacimiento_cliente(faker.date().past(365, TimeUnit.DAYS));
            newCliente.setActivo_cliente(faker.bool().bool());

            clienteRepository.save(newCliente);
        }

        //GENERAR PROFESORES

        for (int i = 0; i < CANTIDAD_DE_PROFESOR; i++){

            Profesor newProfesor = new Profesor();
            //newProfesor.setId_profesor(i+1);
            newProfesor.setRun_profesor(faker.number().numberBetween(1000000, 30000000));
            newProfesor.setDv_profesor(String.valueOf(faker.number().numberBetween(0,9)));
            newProfesor.setPnombre_profesor(faker.clashOfClans().defensiveBuilding());
            newProfesor.setSnombre_profesor(faker.clashOfClans().troop());
            newProfesor.setAppaterno_profesor(faker.minecraft().itemName());
            newProfesor.setApmaterno_profesor(faker.onePiece().character());
            newProfesor.setCorreo_profesor(faker.internet().emailAddress());
            newProfesor.setContrasena_profesor(faker.internet().password(6,10));
            newProfesor.setFecha_nacimiento_profesor(faker.date().past(365, TimeUnit.DAYS));

            profesorRepository.save(newProfesor);
        }

        //GENERAR CURSOS

        for (int i = 0; i < CANTIDAD_DE_CURSO; i++){

            Curso newCurso = new Curso();
            //newCurso.setId_curso(i+1);
            newCurso.setNombre_curso(faker.educator().course());
            newCurso.setFecha_creacion_curso(faker.date().past(365, TimeUnit.DAYS));

            cursoRepository.save(newCurso);
        }

        //GENERAR EVALUACIONES

        for (int i = 0; i < CANTIDAD_DE_EVALUACION; i++) {

            Evaluacion newEvaluacion = new Evaluacion();
            //newEvaluacion.setId_eva(i+1);
            newEvaluacion.setNombre_eva(faker.pokemon().name());
            newEvaluacion.setPonderacion_eva(faker.number().randomDouble(2,10,99));

            evaluacionRepository.save(newEvaluacion);

        }

        //GENERAR INSCRIPCION

        for (int i = 0; i < CANTIDAD_DE_INSCRIPCION; i++) {

            Inscripcion newInscripcion = new Inscripcion();
            //newInscripcion.setId_ins(i+1);
            newInscripcion.setFecha_inscripcion_ins(faker.date().past(365, TimeUnit.DAYS));

            inscripcionRepository.save(newInscripcion);

        }

        //GENERAR MATERIA

        /*for (int i = 0; i < CANTIDAD_DE_MATERIA; i++) {

            Materia newMateria = new Materia();
            //newMateria.setId(i+1);
            newMateria.setNombre_materia(faker.leagueOfLegends().champion());

            materiaRepository.save(newMateria);

        }*/

        //GENERAR MATERIAL

        for (int i = 0; i < CANTIDAD_DE_MATERIAL; i++) {

            Material material1 = new Material();
            //material1.setId_material(i+1);
            material1.setDescripcion_material(faker.dungeonsAndDragons().rangedWeapons());
            material1.setUrl_material(faker.internet().domainName());

            materialRepository.save(material1);
        }





    }
}
