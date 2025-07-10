package CoffeTeamSF.EduTech;
/*
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
*/
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//import CoffeTeamSF.EduTech.dto.ContenidoAlmacenarDTO;
//import CoffeTeamSF.EduTech.model.Contenido;
//import CoffeTeamSF.EduTech.model.Curso;
import CoffeTeamSF.EduTech.repository.ContenidoRepository;
import CoffeTeamSF.EduTech.repository.CursoRepository;
import CoffeTeamSF.EduTech.service.ContenidoService;





@ExtendWith(MockitoExtension.class)
public class ContenidoServiceTest {

    @Mock
    private ContenidoRepository contenidoRepository;

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private ContenidoService contenidoService;



    // @Test
    // void testAlmacenarContenidoCuandoContenidoYaExiste() {
    //     ContenidoAlmacenarDTO contenidoDTO = new ContenidoAlmacenarDTO("C2", "Nombre2", "Desc2", "Material2", "CURSO2");

    //     when(cursoRepository.existsById("CURSO2")).thenReturn(true);
    //     when(contenidoRepository.findById("C2")).thenReturn(Optional.of(new Contenido()));

    //     String resultado = contenidoService.almacenar(contenidoDTO);

    //     assertEquals("El contenido de id C2 ya existe.", resultado);
    //     verify(contenidoRepository, never()).save(any());
    // }



    // @Test
    // void testAlmacenarContenidoExitosamente() {
    //     ContenidoAlmacenarDTO contenidoDTO = new ContenidoAlmacenarDTO("C3", "Nombre3", "Desc3", "Material3", "CURSO3");
    //     Curso curso = new Curso();
    //     curso.setSigla("CURSO3");

    //     when(cursoRepository.existsById("CURSO3")).thenReturn(true);
    //     when(contenidoRepository.findById("C3")).thenReturn(Optional.empty());
    //     when(cursoRepository.findById("CURSO3")).thenReturn(Optional.of(curso));

    //     String resultado = contenidoService.almacenar(contenidoDTO);

    //     assertEquals("Contenido almacenado correctamente.", resultado);
    //     verify(contenidoRepository, times(1)).save(any(Contenido.class));
    // }




    // @Test
    // void testListarContenidos() {
    //     Contenido c1 = new Contenido();
    //     c1.setId("1");
    //     c1.setNombre("Nombre1");
    //     c1.setDescripcion("Desc1");
    //     c1.setMaterial("Material1");

    //     Contenido c2 = new Contenido();
    //     c2.setId("2");
    //     c2.setNombre("Nombre2");
    //     c2.setDescripcion("Desc2");
    //     c2.setMaterial("Material2");

    //     List<Contenido> listaSimulada = Arrays.asList(c1, c2);

    //     when(contenidoRepository.findAll()).thenReturn(listaSimulada);

    //     List<Contenido> resultado = contenidoService.listar();

    //     assertEquals(2, resultado.size());
    //     assertEquals("Nombre1", resultado.get(0).getNombre());
    //     assertEquals("Nombre2", resultado.get(1).getNombre());
    // }



    // @Test
    // void testEliminarContenidoCuandoExiste() {
    //     Contenido contenidoExistente = new Contenido();
    //     contenidoExistente.setId("C5");

    //     when(contenidoRepository.findById("C5")).thenReturn(Optional.of(contenidoExistente));

    //     String resultado = contenidoService.eliminarContenido("C5");

    //     assertEquals("Contenido eliminado correctamente.", resultado);
    //     verify(contenidoRepository, times(1)).delete(contenidoExistente);
    // }



    // @Test
    // void testEliminarContenidoCuandoNoExiste() {
    //     when(contenidoRepository.findById("NO-EXISTE")).thenReturn(Optional.empty());

    //     String resultado = contenidoService.eliminarContenido("NO-EXISTE");

    //     assertEquals("Contenido no existe para poder ser eliminado.", resultado);
    //     verify(contenidoRepository, never()).delete(any());
    // }



}