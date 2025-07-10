package CoffeTeamSF.EduTech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import CoffeTeamSF.EduTech.dto.CursoListarDTO;
import CoffeTeamSF.EduTech.model.Curso;
import CoffeTeamSF.EduTech.repository.CursoRepository;
import CoffeTeamSF.EduTech.service.CursoService;


//Se indica que esta clase trabajará con la extensión Mockito
@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {


    //Paso 1: Se invoca un objeto CursoRepository falso (Gracias a @Mock), esto permite que cursoRepository falso sea moldeable.
    @Mock 
    private CursoRepository cursoRepository; 
    //Esto permite que tengamos nuestro Mock de CursoRepository disponible para inyectarlo a cualquier otro objeto.



    //Paso 2: Se inyecta el mock CursoRepository en un objeto real de CursoService
    @InjectMocks
    private CursoService cursoService; 



    // @Test
    // void testAlmacenarCuandoYaExiste() {
    //     CursoAlmacenarDTO ingresaCursoDTO = new CursoAlmacenarDTO("YYZ28", "Fullstack I", "Enseña Fullstack Básico");
        
    //     when(cursoRepository.findById("YYZ28")).thenReturn(Optional.of(new Curso()));

    //     String resultado = cursoService.almacenar(ingresaCursoDTO);

    //     assertEquals("El curso con sigla YYZ28 ya existe.", resultado);
    // }




    // @Test
    // void testAlmacenarCuandoNoExiste() {
    //     CursoAlmacenarDTO dto = new CursoAlmacenarDTO("INF-456", "Bases de Datos", "MySQL y JPA");

    //     when(cursoRepository.findById("INF-456")).thenReturn(Optional.empty());

    //     String resultado = cursoService.almacenar(dto);

    //     assertEquals("Curso registrado exitosamente", resultado);
    //     verify(cursoRepository, times(1)).save(any(Curso.class));
    // }



    @Test
    void testListar() {
        // Arrange
        Curso c1 = new Curso();
        c1.setSigla("MAT-101");
        c1.setNombre("Matemáticas");
        c1.setDescripcion("Álgebra y cálculo");
        c1.setContenido(new ArrayList<>());
        c1.setInscripciones(new ArrayList<>());
        c1.setEvaluaciones(new ArrayList<>());
        c1.setResenas(new ArrayList<>());

        Curso c2 = new Curso();
        c2.setSigla("FIS-202");
        c2.setNombre("Física");
        c2.setDescripcion("Mecánica clásica");
        c2.setContenido(new ArrayList<>());
        c2.setInscripciones(new ArrayList<>());
        c2.setEvaluaciones(new ArrayList<>());
        c2.setResenas(new ArrayList<>());
        
        when(cursoRepository.findAll()).thenReturn(Arrays.asList(c1, c2));
        List<CursoListarDTO> resultado = cursoService.listar();
        assertNotNull(resultado);
        assertEquals(2, resultado.size());
        assertEquals("MAT-101", resultado.get(0).getSigla());
        assertEquals("FIS-202", resultado.get(1).getSigla());
    }



    @Test
    void testEliminarCursoCuandoExiste() {
        Curso cursoExistente = new Curso();
        cursoExistente.setSigla("INF-999");

        when(cursoRepository.findById("INF-999")).thenReturn(Optional.of(cursoExistente));

        String resultado = cursoService.eliminarCurso("INF-999");

        assertEquals("Curso eliminado correctamente.", resultado);
        verify(cursoRepository, times(1)).delete(cursoExistente);
    }



    @Test
    void testEliminarCursoCuandoNoExiste() {
        when(cursoRepository.findById("NO-EXISTE")).thenReturn(Optional.empty());

        String resultado = cursoService.eliminarCurso("NO-EXISTE");

        assertEquals("Curso no existe para poder ser eliminado.", resultado);
    }


    
}