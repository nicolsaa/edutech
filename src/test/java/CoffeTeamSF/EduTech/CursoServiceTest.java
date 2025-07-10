package CoffeTeamSF.EduTech;

import CoffeTeamSF.EduTech.dto.AsignarProfesorDTO;
import CoffeTeamSF.EduTech.dto.CursoAlmacenarDTO;
import CoffeTeamSF.EduTech.dto.CursoDTOputmapping;
import CoffeTeamSF.EduTech.dto.CursoListarDTO;
import CoffeTeamSF.EduTech.model.Curso;
import CoffeTeamSF.EduTech.model.TipoUsuario;
import CoffeTeamSF.EduTech.model.Usuario;
import CoffeTeamSF.EduTech.repository.CursoRepository;
import CoffeTeamSF.EduTech.repository.UsuarioRepository;
import CoffeTeamSF.EduTech.service.CursoService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

    @ExtendWith({MockitoExtension.class})
    public class CursoServiceTest {
    @Mock
    private CursoRepository cursoRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @InjectMocks
    private CursoService cursoService;

    public CursoServiceTest() {
    }

    @Test
    void testAlmacenarCursoCorrectamente() {
        CursoAlmacenarDTO dto = new CursoAlmacenarDTO("INF456", "Base de Datos", "Curso SQL", 10L);
        Usuario gerente = new Usuario();
        TipoUsuario tipoGerente = new TipoUsuario();
        tipoGerente.setNombre("GerenteCursos");
        gerente.setTipoUsuario(tipoGerente);
        Mockito.when(this.cursoRepository.findById("INF456")).thenReturn(Optional.empty());
        Mockito.when(this.usuarioRepository.findById(10L)).thenReturn(Optional.of(gerente));
        ResponseEntity<String> respuesta = this.cursoService.almacenar(dto);
        Assertions.assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        Assertions.assertEquals("Curso registrado exitosamente.", respuesta.getBody());
        ((CursoRepository)Mockito.verify(this.cursoRepository, Mockito.times(1))).save((Curso)ArgumentMatchers.any(Curso.class));
    }

    @Test
    void testAlmacenarCursoFallaPorCursoExistente() {
        CursoAlmacenarDTO dto = new CursoAlmacenarDTO("INF456", "Base de Datos", "Curso SQL", 10L);
        Curso cursoExistente = new Curso();
        cursoExistente.setSigla("INF456");
        Mockito.when(this.cursoRepository.findById("INF456")).thenReturn(Optional.of(cursoExistente));
        ResponseEntity<String> respuesta = this.cursoService.almacenar(dto);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, respuesta.getStatusCode());
        Assertions.assertTrue(((String)respuesta.getBody()).contains("ya existe"));
        ((CursoRepository)Mockito.verify(this.cursoRepository, Mockito.never())).save((Curso)ArgumentMatchers.any(Curso.class));
    }

    @Test
    void testListarCursosRetornaLista() {
        Curso c1 = new Curso();
        c1.setSigla("INF101");
        c1.setNombre("Intro a informática");
        c1.setDescripcion("Curso básico");
        c1.setInscripciones(new ArrayList());
        c1.setEvaluaciones(new ArrayList());
        c1.setResenas(new ArrayList());
        c1.setContenido(new ArrayList());
        Curso c2 = new Curso();
        c2.setSigla("MAT202");
        c2.setNombre("Matemáticas");
        c2.setDescripcion("Curso avanzado");
        c2.setInscripciones(new ArrayList());
        c2.setEvaluaciones(new ArrayList());
        c2.setResenas(new ArrayList());
        c2.setContenido(new ArrayList());
        List<Curso> cursos = Arrays.asList(c1, c2);
        Mockito.when(this.cursoRepository.findAll()).thenReturn(cursos);
        List<CursoListarDTO> resultado = this.cursoService.listar();
        Assertions.assertEquals(2, resultado.size());
        Assertions.assertEquals("INF101", ((CursoListarDTO)resultado.get(0)).getSigla());
        Assertions.assertEquals("Matemáticas", ((CursoListarDTO)resultado.get(1)).getNombre());
    }

    @Test
    void testListarCursosRetornaListaVacia() {
        Mockito.when(this.cursoRepository.findAll()).thenReturn(Collections.emptyList());
        List<CursoListarDTO> resultado = this.cursoService.listar();
        Assertions.assertNotNull(resultado);
        Assertions.assertTrue(resultado.isEmpty());
    }

    @Test
    void testModificarCursoExistente() {
        Curso curso = new Curso();
        curso.setSigla("INF100");
        curso.setNombre("Antiguo Nombre");
        curso.setDescripcion("Antigua Descripción");
        CursoDTOputmapping dto = new CursoDTOputmapping();
        dto.setNombre("Nuevo Nombre");
        dto.setDescripcion("Nueva Descripción");
        Mockito.when(this.cursoRepository.findById("INF100")).thenReturn(Optional.of(curso));
        String resultado = this.cursoService.modificarCurso("INF100", dto);
        Assertions.assertEquals("Curso modificado correctamente.", resultado);
        Assertions.assertEquals("Nuevo Nombre", curso.getNombre());
        Assertions.assertEquals("Nueva Descripción", curso.getDescripcion());
        ((CursoRepository)Mockito.verify(this.cursoRepository, Mockito.times(1))).save(curso);
    }

    @Test
    void testModificarCursoNoExistente() {
        CursoDTOputmapping dto = new CursoDTOputmapping();
        dto.setNombre("Nuevo Nombre");
        dto.setDescripcion("Nueva Descripción");
        Mockito.when(this.cursoRepository.findById("INF999")).thenReturn(Optional.empty());
        String resultado = this.cursoService.modificarCurso("INF999", dto);
        Assertions.assertEquals("Curso no se encuentra para ser modificado.", resultado);
        ((CursoRepository)Mockito.verify(this.cursoRepository, Mockito.never())).save((Curso)ArgumentMatchers.any());
    }

    @Test
    void testEliminarCursoExistente() {
        Curso curso = new Curso();
        curso.setSigla("INF200");
        Mockito.when(this.cursoRepository.findById("INF200")).thenReturn(Optional.of(curso));
        String resultado = this.cursoService.eliminarCurso("INF200");
        Assertions.assertEquals("Curso eliminado correctamente.", resultado);
        ((CursoRepository)Mockito.verify(this.cursoRepository, Mockito.times(1))).delete(curso);
    }

    @Test
    void testEliminarCursoNoExistente() {
        Mockito.when(this.cursoRepository.findById("INF999")).thenReturn(Optional.empty());
        String resultado = this.cursoService.eliminarCurso("INF999");
        Assertions.assertEquals("Curso no existe para poder ser eliminado.", resultado);
        ((CursoRepository)Mockito.verify(this.cursoRepository, Mockito.never())).delete((Curso)ArgumentMatchers.any());
    }

    @Test
    void testAsignarProfesorCorrectamente() {
        AsignarProfesorDTO dto = new AsignarProfesorDTO();
        dto.setIdGerente(1L);
        dto.setSiglaCurso("INF300");
        dto.setProfesor_a_asignar(2L);
        Usuario gerente = new Usuario();
        TipoUsuario tipoGerente = new TipoUsuario();
        tipoGerente.setNombre("GerenteCursos");
        gerente.setTipoUsuario(tipoGerente);
        Usuario profesor = new Usuario();
        TipoUsuario tipoProfesor = new TipoUsuario();
        tipoProfesor.setNombre("Profesor");
        profesor.setTipoUsuario(tipoProfesor);
        profesor.setNombre("Juan");
        profesor.setApellido("Perez");
        Curso curso = new Curso();
        curso.setSigla("INF300");
        curso.setProfesor((String)null);
        Mockito.when(this.usuarioRepository.findById(1L)).thenReturn(Optional.of(gerente));
        Mockito.when(this.cursoRepository.findById("INF300")).thenReturn(Optional.of(curso));
        Mockito.when(this.usuarioRepository.findById(2L)).thenReturn(Optional.of(profesor));
        ResponseEntity<String> respuesta = this.cursoService.asignarProfesorACurso(dto);
        Assertions.assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        Assertions.assertEquals("Profesor asignado correctamente al curso.", respuesta.getBody());
        Assertions.assertEquals("Juan Perez", curso.getProfesor());
        ((CursoRepository)Mockito.verify(this.cursoRepository, Mockito.times(1))).save(curso);
    }

    @Test
    void testAsignarProfesorCursoConProfesorAsignado() {
        AsignarProfesorDTO dto = new AsignarProfesorDTO();
        dto.setIdGerente(1L);
        dto.setSiglaCurso("INF400");
        dto.setProfesor_a_asignar(2L);
        Usuario gerente = new Usuario();
        TipoUsuario tipoGerente = new TipoUsuario();
        tipoGerente.setNombre("GerenteCursos");
        gerente.setTipoUsuario(tipoGerente);
        Curso curso = new Curso();
        curso.setSigla("INF400");
        curso.setProfesor("Profesor Existente");
        Mockito.when(this.usuarioRepository.findById(1L)).thenReturn(Optional.of(gerente));
        Mockito.when(this.cursoRepository.findById("INF400")).thenReturn(Optional.of(curso));
        ResponseEntity<String> respuesta = this.cursoService.asignarProfesorACurso(dto);
        Assertions.assertEquals(HttpStatus.CONFLICT, respuesta.getStatusCode());
        Assertions.assertTrue(((String)respuesta.getBody()).contains("ya tiene un profesor asignado"));
        ((CursoRepository)Mockito.verify(this.cursoRepository, Mockito.never())).save((Curso)ArgumentMatchers.any());
    }
}
