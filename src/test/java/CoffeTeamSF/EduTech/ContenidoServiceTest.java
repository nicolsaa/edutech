package CoffeTeamSF.EduTech;


import CoffeTeamSF.EduTech.dto.ContenidoAlmacenarDTO;
import CoffeTeamSF.EduTech.dto.ContenidoDTOputmapping;
import CoffeTeamSF.EduTech.model.Contenido;
import CoffeTeamSF.EduTech.model.Curso;
import CoffeTeamSF.EduTech.model.TipoUsuario;
import CoffeTeamSF.EduTech.model.Usuario;
import CoffeTeamSF.EduTech.repository.ContenidoRepository;
import CoffeTeamSF.EduTech.repository.CursoRepository;
import CoffeTeamSF.EduTech.repository.UsuarioRepository;
import CoffeTeamSF.EduTech.service.ContenidoService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith({MockitoExtension.class})
public class ContenidoServiceTest {
    @Mock
    private ContenidoRepository contenidoRepository;
    @Mock
    private CursoRepository cursoRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @InjectMocks
    private ContenidoService contenidoService;

    public ContenidoServiceTest() {
    }

    @Test
    void testAlmacenarContenidoCorrectamente() {
        ContenidoAlmacenarDTO dto = new ContenidoAlmacenarDTO("Nombre1", "Desc1", "Material1", "CURSO1", 1L);
        Usuario profesor = new Usuario();
        TipoUsuario tipoUsuario = new TipoUsuario();
        tipoUsuario.setNombre("Profesor");
        profesor.setTipoUsuario(tipoUsuario);
        Mockito.when(this.usuarioRepository.findById(1L)).thenReturn(Optional.of(profesor));
        Curso curso = new Curso();
        curso.setSigla("CURSO1");
        Mockito.when(this.cursoRepository.findById("CURSO1")).thenReturn(Optional.of(curso));
        String resultado = this.contenidoService.almacenar(dto);
        Assertions.assertEquals("Contenido almacenado correctamente.", resultado);
        ((ContenidoRepository)Mockito.verify(this.contenidoRepository)).save((Contenido)ArgumentMatchers.any(Contenido.class));
    }

    @Test
    void testAlmacenarContenidoFallaPorUsuarioNoExistente() {
        ContenidoAlmacenarDTO dto = new ContenidoAlmacenarDTO("Nombre1", "Desc1", "Material1", "CURSO1", 99L);
        Mockito.when(this.usuarioRepository.findById(99L)).thenReturn(Optional.empty());
        String resultado = this.contenidoService.almacenar(dto);
        Assertions.assertEquals("ID de profesor no existe en el sistema.", resultado);
        ((ContenidoRepository)Mockito.verify(this.contenidoRepository, Mockito.never())).save((Contenido)ArgumentMatchers.any());
    }

    @Test
    void testListarContenidosRetornaLista() {
        Contenido c1 = new Contenido();
        c1.setId(1L);
        c1.setNombre("Cont1");
        Contenido c2 = new Contenido();
        c2.setId(2L);
        c2.setNombre("Cont2");
        Mockito.when(this.contenidoRepository.findAll()).thenReturn(Arrays.asList(c1, c2));
        List<Contenido> resultado = this.contenidoService.listar();
        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals("Cont1", ((Contenido)resultado.get(0)).getNombre());
    }

        @Test
    void testListarContenidosRetornaListaVacia() {
        Mockito.when(this.contenidoRepository.findAll()).thenReturn(Arrays.asList());
        List<Contenido> resultado = this.contenidoService.listar();
        Assertions.assertTrue(resultado.isEmpty());
    }

    @Test
    void testModificarContenidoCorrectamente() {
        Contenido contenidoExistente = new Contenido();
        contenidoExistente.setId(1L);
        contenidoExistente.setNombre("OldName");
        contenidoExistente.setDescripcion("OldDesc");
        contenidoExistente.setMaterial("OldMaterial");
        ContenidoDTOputmapping dto = new ContenidoDTOputmapping();
        dto.setNombre("NewName");
        dto.setDescripcion("NewDesc");
        dto.setMaterial("NewMaterial");
        Mockito.when(this.contenidoRepository.findById(1L)).thenReturn(Optional.of(contenidoExistente));
        String resultado = this.contenidoService.modificarContenido(1L, dto);
        Assertions.assertEquals("Contenido modificado correctamente.", resultado);
        Assertions.assertEquals("NewName", contenidoExistente.getNombre());
        Assertions.assertEquals("NewDesc", contenidoExistente.getDescripcion());
        Assertions.assertEquals("NewMaterial", contenidoExistente.getMaterial());
        ((ContenidoRepository)Mockito.verify(this.contenidoRepository)).save(contenidoExistente);
    }

    @Test
    void testModificarContenidoNoExiste() {
        ContenidoDTOputmapping dto = new ContenidoDTOputmapping();
        dto.setNombre("NewName");
        Mockito.when(this.contenidoRepository.findById(99L)).thenReturn(Optional.empty());
        String resultado = this.contenidoService.modificarContenido(99L, dto);
        Assertions.assertEquals("Contenido no se encuentra para ser modificado.", resultado);
        ((ContenidoRepository)Mockito.verify(this.contenidoRepository, Mockito.never())).save((Contenido)ArgumentMatchers.any());
    }

    @Test
    void testEliminarContenidoCorrectamente() {
        Contenido contenidoExistente = new Contenido();
        contenidoExistente.setId(1L);
        Mockito.when(this.contenidoRepository.findById(1L)).thenReturn(Optional.of(contenidoExistente));
        String resultado = this.contenidoService.eliminarContenido(1L);
        Assertions.assertEquals("Contenido eliminado correctamente.", resultado);
        ((ContenidoRepository)Mockito.verify(this.contenidoRepository)).delete(contenidoExistente);
    }

    @Test
    void testEliminarContenidoNoExiste() {
        Mockito.when(this.contenidoRepository.findById(99L)).thenReturn(Optional.empty());
        String resultado = this.contenidoService.eliminarContenido(99L);
        Assertions.assertEquals("Contenido no existe para poder ser eliminado.", resultado);
        ((ContenidoRepository)Mockito.verify(this.contenidoRepository, Mockito.never())).delete((Contenido)ArgumentMatchers.any());
    }
}
