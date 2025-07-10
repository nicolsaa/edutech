package CoffeTeamSF.EduTech.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import CoffeTeamSF.EduTech.dto.InscripcionDTO;
import CoffeTeamSF.EduTech.dto.ResenaListarDTO2;
import CoffeTeamSF.EduTech.dto.TipoUsuarioDTO;
import CoffeTeamSF.EduTech.dto.UsuarioDTO;
import CoffeTeamSF.EduTech.model.Incidencia;
import CoffeTeamSF.EduTech.model.Resena;
import CoffeTeamSF.EduTech.model.TipoUsuario;
import CoffeTeamSF.EduTech.model.Usuario;
import CoffeTeamSF.EduTech.repository.TipoUsuarioRepository;
import CoffeTeamSF.EduTech.repository.UsuarioRepository;
import jakarta.transaction.Transactional;


@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    /*METODO QUE REGISTRA USUARIOS
    BY: NICOL SAAVEDRA
     */
    public String registrarUsuario(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.findByCorreo(usuarioDTO.getCorreo())== null){
            Usuario usuario = new Usuario();

            usuario.setCorreo(usuarioDTO.getCorreo());
            usuario.setApellido(usuarioDTO.getApellido());
            usuario.setNombre(usuarioDTO.getNombre());

            usuarioRepository.save(usuario);
            return "Usuario registrado exitosamente.";
        } else {
            return "El usuario con correo " + usuarioDTO.getCorreo() + " ya existe";
        }
    }
    

    /*METODO QUE LISTA LOS USUARIOS con su relacion de tipo de ususario
    BY: NICOL SAAVEDRA
     */
    public List<UsuarioDTO> obtenerUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuariosDTO = new ArrayList<>();

        for (Usuario usuario : usuarios) {
            String tipoUsuario = null;
            if (usuario.getTipoUsuario() != null) {
                tipoUsuario = usuario.getTipoUsuario().getNombre();
            }

            List<InscripcionDTO> inscripciones = 
                usuario.getInscripciones().stream()
                .map(inscripcion -> 
                    new InscripcionDTO(inscripcion.getUsuario().getNombre(), inscripcion.getFechaInscripcion()))
                    .collect(Collectors.toList());

            //Añadido nuevo para Incidencias (Made by Vicente Aravena)
            List<Incidencia> incidencias = usuario.getIncidencias();

            //Añadido nuevo para Resena (Made by Vicente Aravena)
            List<ResenaListarDTO2> resenasDTO = new ArrayList<>();
            if (usuario.getResenas() != null) {
                for (Resena resena : usuario.getResenas()) {
                    resenasDTO.add(
                        new ResenaListarDTO2(
                            String.valueOf(resena.getidResena()),
                            resena.getCurso() != null ? resena.getCurso().getNombre() : null,
                            resena.getComentario(),
                            resena.getCalificacion(),
                            resena.getFecha() != null ? resena.getFecha().toString() : null
                        )
                    );
                }
            }
            UsuarioDTO usuarioDTO = new UsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getCorreo(),
                tipoUsuario,
                inscripciones,
                incidencias,
                resenasDTO
            );
            usuariosDTO.add(usuarioDTO);
        }
        return usuariosDTO;
    }

    /*metodo pra listas por tipo de usuario
     * by Nicol Saavedra
     */
    public List<Usuario> listarUsuariosPorTipoNombre(String nombreTipo){
        return usuarioRepository.findByTipoUsuario(nombreTipo);
    }


    //get usuario by id
    public UsuarioDTO getUsuarioById(long id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);

        if(usuarioOpt.isPresent()) {
            UsuarioDTO usuarioDTO = new UsuarioDTO();

            usuarioDTO.setApellido(usuarioOpt.get().getApellido());
            usuarioDTO.setCorreo(usuarioOpt.get().getCorreo());
            usuarioDTO.setNombre(usuarioOpt.get().getNombre());

            //hacer if y evitar NPE null pointer exception
            usuarioDTO.setTipoUsuario(usuarioOpt.get().getTipoUsuario().getNombre());

            return usuarioDTO;
        } else {
            return null;
        }
    }



    /*METODO QUE ASIGNA TIPO DE USUARIO A UN USUARIO
    BY: NICOL SAAVEDRA
     */
    public String asignarTipoUsuario(TipoUsuarioDTO tipoUsuarioDTO) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(tipoUsuarioDTO.getIdUsuario());
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            TipoUsuario tipoUsuario = this.tipoUsuarioRepository.findById(tipoUsuarioDTO.getIdTipoUsuario()).get();
            usuario.setTipoUsuario(tipoUsuario);
            usuarioRepository.save(usuario); 
            return "Tipo de usuario asignado correctamente";
        }else{
            return "Usuario o tipo de usuario no encontrado";
        }
    }


    /*METODO QUE ELIMINA UN USUARIO
     * BY: NICOL SAAVEDRA
     */
    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<String> eliminarUsuarioPorCorreo(String correo){
        if(usuarioRepository.findByCorreo(correo)== null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("Usuario con correo " + correo + " no encontrado");
        }
        usuarioRepository.deleteByCorreo(correo);
        return ResponseEntity.ok("Usuario eliminado correctamente");
    }



    /*METODO QUE MODIFICA UN USUSARIO
     * BY NICOL SAAVEDRA
     */
    public String modificarUsusario (UsuarioDTO usuarioDTO){
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioDTO.getId());

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();

            if (usuarioDTO.getNombre() != null) {
                usuario.setNombre(usuarioDTO.getNombre());
            }
            if (usuarioDTO.getApellido()!= null){
                usuario.setApellido(usuarioDTO.getApellido());
            }
            if (usuarioDTO.getCorreo() != null) {
                usuario.setCorreo(usuarioDTO.getCorreo());
            }
            usuarioRepository.save(usuario);
            return "Usuario modificado correctamente";
        }else{
            return "Usuario no encontrado";
        }
    }


}
