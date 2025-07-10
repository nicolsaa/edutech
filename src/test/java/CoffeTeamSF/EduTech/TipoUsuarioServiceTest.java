package CoffeTeamSF.EduTech;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import CoffeTeamSF.EduTech.dto.TipoUsuarioDTO;
import CoffeTeamSF.EduTech.model.TipoUsuario;
import CoffeTeamSF.EduTech.repository.TipoUsuarioRepository;
import CoffeTeamSF.EduTech.service.TipoUsuarioService;

/*TEST DE TIPO DE USUARIOS
  * BY NICOL SAAVEDRA
  */
@ExtendWith(MockitoExtension.class)
public class TipoUsuarioServiceTest {
    @Mock
    private TipoUsuarioRepository tipoUsuarioRepository;

    @InjectMocks
    private TipoUsuarioService tipoUsuarioService;

    /*TEST QUE VE EL COMPORTAMIENTO DEL METODO ALMACENAR CUANDO EL TIPO DE USUARIO NO EXISTE
     * BY NICOL SAAVEDRA
     */
    @Test
    public void cuandoTipoUsuarioNoExiste(){
        TipoUsuarioDTO tipoUsuarioDTO = new TipoUsuarioDTO();
        tipoUsuarioDTO.setNombre("Administrador");

        when(tipoUsuarioRepository.findByNombre("Administrador")).thenReturn(null);

        String resultado = tipoUsuarioService.almacenar(tipoUsuarioDTO);

        assertEquals("Tipo de usuario Administrador almacenado correctamente", resultado);

        verify(tipoUsuarioRepository).findByNombre(anyString());
    }

    /*TEST QUE VE EL COMPORTAMIENTO DEL METODO ALMACENAR 
     * BY NICOL SAAVEDRA
     */
    @Test
    public void cuandoTipoUsuarioYaExiste(){
        TipoUsuarioDTO tipoUsuarioDTO = new TipoUsuarioDTO();
        tipoUsuarioDTO.setNombre("Docente");

        TipoUsuario existente = new TipoUsuario();

        when(tipoUsuarioRepository.findByNombre("Docente")).thenReturn(existente);

        String resultado = tipoUsuarioService.almacenar(tipoUsuarioDTO);

        assertEquals("El tipo de usuario con nombre: Docente ya existe", resultado);
        verify(tipoUsuarioRepository).findByNombre(anyString());
    }
    
    /*TEST QUE VE EL COMPORTAMIENTO DEL METODO LISTAR 
     * BY NICOL SAAVEDRA
     */
    @Test
    public void testListarTipoUsuario(){
        TipoUsuario tipoUsuario1 = new TipoUsuario();
        tipoUsuario1.setNombre("Alumno");

        TipoUsuario tipoUsuario2 = new TipoUsuario();
        tipoUsuario2.setNombre("Soporte");

        List <TipoUsuario> tipoUsuariosList = new ArrayList<>();
        tipoUsuariosList.add(tipoUsuario1);
        tipoUsuariosList.add(tipoUsuario2);

        when(tipoUsuarioRepository.findAll()).thenReturn(tipoUsuariosList);
        List<TipoUsuario> resultado = tipoUsuarioService.listar();

        assertFalse(resultado.isEmpty());
        assertTrue(resultado.size() == tipoUsuariosList.size());

        assertEquals(tipoUsuario1.getNombre(), resultado.get(0).getNombre());
        assertEquals(tipoUsuario2.getNombre(), resultado.get(1).getNombre());

        verify(tipoUsuarioRepository).findAll();
    }

    /*TEST QUE VE EL COMPORTAMIENTO DEL METODO MODIFICAR EL TIPO DE USUARIO CUANDO EL TIPO DE USUARIO YA EXISTE
    Y LO CAMBIA POR UN NUEVO NOMBRE
     * BY NICOL SAAVEDRA
     */
    @Test
    public void cuantoTipoUsuarioExisteYNombreEsNull(){
        Long id = 1L;
        TipoUsuarioDTO tipoUsuarioDTO = new TipoUsuarioDTO();
        tipoUsuarioDTO.setIdTipoUsuario(id);
        tipoUsuarioDTO.setNombre("Profesor"); //nuevo nombre

        TipoUsuario existe = new TipoUsuario();
        existe.setId(id);
        existe.setNombre("Docente"); // nombre anterior

        when(tipoUsuarioRepository.findById(id)).thenReturn(Optional.of(existe));

        String resultado = tipoUsuarioService.modificarTipoUsuario(tipoUsuarioDTO);

        assertEquals("Tipo de usuario modificado correctamente", resultado);
        verify(tipoUsuarioRepository).save(existe);
        assertEquals("Profesor", existe.getNombre());
    }

    /*TEST QUE VE EL COMPORTAMIENTO DEL METODO MODIFICAR EL TIPO DE USUARIO CUANDO EL TIPO DE USUARIO YA EXISTE
    Y SE QUIERE CAMBIAR POR EXACTAMENTE EL MISMO NOMBRE
     * BY NICOL SAAVEDRA
     */
    @Test
    public void cuandoNombreEsIgual(){
        Long id = 1L;
        String mismoNombre = "Docente";

        TipoUsuarioDTO tipoUsuarioDTO = new TipoUsuarioDTO();
        tipoUsuarioDTO.setIdTipoUsuario(id);
        tipoUsuarioDTO.setNombre(mismoNombre);

        TipoUsuario existente = new TipoUsuario();
        existente.setId(id);
        existente.setNombre(mismoNombre);

        when(tipoUsuarioRepository.findById(id)).thenReturn(Optional.of(existente));

        String resultado = tipoUsuarioService.modificarTipoUsuario(tipoUsuarioDTO);

        assertEquals("Tipo de usuario modificado correctamente", resultado);
        verify(tipoUsuarioRepository).save(existente);
        assertEquals(mismoNombre, existente.getNombre());
    }

}
