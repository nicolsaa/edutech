package CoffeTeamSF.EduTech;

import CoffeTeamSF.EduTech.dto.ResenaAlmacenarDTO;
import CoffeTeamSF.EduTech.dto.ResenaListarDTO3;
import CoffeTeamSF.EduTech.model.Curso;
import CoffeTeamSF.EduTech.model.Resena;
import CoffeTeamSF.EduTech.model.Usuario;
import CoffeTeamSF.EduTech.repository.CursoRepository;
import CoffeTeamSF.EduTech.repository.ResenaRepository;
import CoffeTeamSF.EduTech.repository.UsuarioRepository;
import CoffeTeamSF.EduTech.service.ResenaService;
import java.time.LocalDate;
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
public class ResenaServiceTest {
    @Mock
    private ResenaRepository resenaRepository;
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private CursoRepository cursoRepository;
    @InjectMocks
    private ResenaService resenaService;

    public ResenaServiceTest() {
    }

    @Test
    void testAlmacenarResenaCorrectamente() {
        ResenaAlmacenarDTO dto = new ResenaAlmacenarDTO(1L, "BD", "Buen curso", 5);
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setNombre("Juan");
        Curso curso = new Curso();
        curso.setNombre("BD");
        Mockito.when(this.usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        Mockito.when(this.cursoRepository.findByNombre("BD")).thenReturn(Optional.of(curso));
        ResponseEntity<String> respuesta = this.resenaService.almacenarResena(dto);
        Assertions.assertEquals(HttpStatus.CREATED, respuesta.getStatusCode());
        Assertions.assertTrue(((String)respuesta.getBody()).contains("registrada exitosamente"));
        ((ResenaRepository)Mockito.verify(this.resenaRepository, Mockito.times(1))).save((Resena)ArgumentMatchers.any(Resena.class));
    }

    @Test
    void testAlmacenarResenaUsuarioNoExiste() {
        ResenaAlmacenarDTO dto = new ResenaAlmacenarDTO(99L, "BD", "Comentario", 4);
        Mockito.when(this.usuarioRepository.findById(99L)).thenReturn(Optional.empty());
        ResponseEntity<String> respuesta = this.resenaService.almacenarResena(dto);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, respuesta.getStatusCode());
        Assertions.assertEquals("Usuario no encontrado. No se puede almacenar reseña.", respuesta.getBody());
        ((ResenaRepository)Mockito.verify(this.resenaRepository, Mockito.never())).save((Resena)ArgumentMatchers.any(Resena.class));
    }

    @Test
    void testListarResenasConDatos() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Pedro");
        Curso curso = new Curso();
        curso.setNombre("Java");
        Resena r1 = new Resena();
        r1.setidResena(1L);
        r1.setComentario("Excelente");
        r1.setCalificacion(5);
        r1.setFecha(LocalDate.of(2024, 10, 1));
        r1.setUsuario(usuario);
        r1.setCurso(curso);
        Mockito.when(this.resenaRepository.findAll()).thenReturn(List.of(r1));
        List<ResenaListarDTO3> resultado = this.resenaService.listarResenas();
        Assertions.assertEquals(1, resultado.size());
        Assertions.assertEquals("Pedro", ((ResenaListarDTO3)resultado.get(0)).getNombreUsuario());
        Assertions.assertEquals("Java", ((ResenaListarDTO3)resultado.get(0)).getCurso());
        Assertions.assertEquals("Excelente", ((ResenaListarDTO3)resultado.get(0)).getComentario());
        Assertions.assertEquals("2024-10-01", ((ResenaListarDTO3)resultado.get(0)).getFecha());
    }

    @Test
    void testListarResenasVacio() {
        Mockito.when(this.resenaRepository.findAll()).thenReturn(Collections.emptyList());
        List<ResenaListarDTO3> resultado = this.resenaService.listarResenas();
        Assertions.assertTrue(resultado.isEmpty());
    }
}

