package CoffeTeamSF.EduTech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import CoffeTeamSF.EduTech.dto.UsuarioDTO;
import CoffeTeamSF.EduTech.model.Usuario;
import CoffeTeamSF.EduTech.repository.UsuarioRepository;
import CoffeTeamSF.EduTech.service.UsuarioService;

/*TEST DE USUARIOS
  * BY NICOL SAAVEDRA
  */

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    /*TEST QUE VE EL COMPORTAMIENTO DEL METODO REGISTRAR USUARIO CUANDO EL USUARIO NO EXISTE
     * BY NICOL SAAVEDRA
     */
    @Test
    public void testAlmacenarUsusarioNoExistente(){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setCorreo("NoCorreo@gmail.com");
        usuarioDTO.setNombre("Fernando");
        usuarioDTO.setApellido("Sepulveda");

        when(usuarioRepository.findByCorreo(usuarioDTO.getCorreo())).thenReturn(null);
        
        String resultado = usuarioService.registrarUsuario(usuarioDTO);

        assertEquals("Usuario registrado exitosamente.", resultado);
        verify(usuarioRepository).findByCorreo(anyString());
    }

    /*TEST QUE VE EL COMPORTAMIENTO DEL METODO REGISTRAR USUARIO CUANDO EL USUARIO YA EXISTE
     * BY NICOL SAAVEDRA
     */
    @Test
    public void testAlmacenarCuandoYaExiste(){
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setCorreo("correo@gmail.com");
        usuarioDTO.setNombre("Vicente");
        usuarioDTO.setApellido("Aravena");

        Usuario usuarioExistente = new Usuario();
        when(usuarioRepository.findByCorreo(usuarioDTO.getCorreo())).thenReturn(usuarioExistente);

        String resultado = usuarioService.registrarUsuario(usuarioDTO);

        assertEquals("El usuario con correo " + usuarioDTO.getCorreo() + " ya existe", resultado);
        verify(usuarioRepository).findByCorreo(any());
    }
    
    /*TEST QUE VE EL COMPORTAMIENTO DEL METODO OBTENER USUARIOS 
     * BY NICOL SAAVEDRA
     */
    @Test
    public void testListarUsuario(){
        Usuario usuario1 = new Usuario();
        usuario1.setNombre("Nicol");
        usuario1.setApellido("Saavedra");
        usuario1.setCorreo("nicol@gmail.com");
        usuario1.setContrasena("9876");

        Usuario usuario2 = new Usuario();
        usuario2.setNombre("Fernando");
        usuario2.setApellido("Sepulveda");
        usuario2.setCorreo("fernando@gmail.com");
        usuario2.setContrasena("5432");

        List <Usuario> usuariosList = new ArrayList<>();
        usuariosList.add(usuario1);
        usuariosList.add(usuario2);

        when(usuarioRepository.findAll()).thenReturn(usuariosList);
        List<UsuarioDTO> resultado = usuarioService.obtenerUsuarios();

        assertFalse(resultado.isEmpty());
        assertTrue(resultado.size() == usuariosList.size());
        
        assertEquals(usuario1.getNombre(), resultado.get(0).getNombre());
        assertEquals(usuario2.getNombre(), resultado.get(1).getNombre());
        verify(usuarioRepository).findAll();
    }

    /*TEST QUE VE EL COMPORTAMIENTO DEL METODO ELIMINAR USUARIOS POR CORREO CUANDO EL USUARIO NO EXISTE
     * BY NICOL SAAVEDRA
     */
    @Test
    public void testEliminarUsuarioPorCorreo_cuandoNoExiste(){
        String correo = "noCorreo@gmail.com";
        when(usuarioRepository.findByCorreo(correo)).thenReturn(null);

        ResponseEntity <String> resultado = usuarioService.eliminarUsuarioPorCorreo(correo);

        assertEquals(HttpStatus.NOT_FOUND, resultado.getStatusCode());
        assertEquals("Usuario con correo " + correo + " no encontrado", resultado.getBody());

        verify(usuarioRepository, never()).deleteByCorreo(anyString());
        verify(usuarioRepository).findByCorreo(anyString());
    }

    /*TEST QUE VE EL COMPORTAMIENTO DEL METODO ELIMINAR USUARIOS POR CORREO CUANDO EL USUARIO YA EXISTE
     * BY NICOL SAAVEDRA
     */
    @Test
    public void testEliminarUsuarioPorCorreo_cuandoExiste(){
        String correo = "correo@gmail.com";
        when(usuarioRepository.findByCorreo(correo)).thenReturn(new Usuario());

        ResponseEntity<String> resultado = usuarioService.eliminarUsuarioPorCorreo(correo);

        assertEquals(HttpStatus.OK, resultado.getStatusCode());
        assertEquals("Usuario eliminado correctamente", resultado.getBody());

        verify(usuarioRepository).deleteByCorreo(correo);
    }

}
