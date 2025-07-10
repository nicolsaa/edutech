package CoffeTeamSF.EduTech.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import CoffeTeamSF.EduTech.dto.ContenidoAlmacenarDTO;
import CoffeTeamSF.EduTech.dto.ContenidoDTOputmapping;
import CoffeTeamSF.EduTech.model.Contenido;
import CoffeTeamSF.EduTech.model.Curso;
import CoffeTeamSF.EduTech.model.Usuario;
import CoffeTeamSF.EduTech.repository.ContenidoRepository;
import CoffeTeamSF.EduTech.repository.CursoRepository;
import CoffeTeamSF.EduTech.repository.UsuarioRepository;


//MADE BY VICENTE ARAVENA
@Service
public class ContenidoService {
    @Autowired
    private ContenidoRepository contenidoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    // Método almacenar contenido
    public String almacenar(ContenidoAlmacenarDTO contenidoDTO){
        // Validar existencia del usuario
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(contenidoDTO.getIdProfesor());
        if (!usuarioOpt.isPresent()) {
            return "ID de profesor no existe en el sistema.";
        }
        // Validar que sea de tipoUsuario Profesor
        Usuario usuario = usuarioOpt.get();
        if (usuario.getTipoUsuario() == null || 
            !usuario.getTipoUsuario().getNombre().equalsIgnoreCase("Profesor")) {
            return "ID no posee permisos para crear contenido.";
        }
        // Validar existencia del curso
        Optional<Curso> cursoOpt = cursoRepository.findById(contenidoDTO.getSigla());
        if (!cursoOpt.isPresent()) { 
            return "Curso no existe para poder almacenar el contenido.";
        }
        // Crear y almacenar contenido
        Contenido contenido = new Contenido();
        contenido.setNombre(contenidoDTO.getNombre()); 
        contenido.setDescripcion(contenidoDTO.getDescripcion());
        contenido.setMaterial(contenidoDTO.getMaterial());
        contenido.setCurso(cursoOpt.get());

        contenidoRepository.save(contenido);

        return "Contenido almacenado correctamente.";
    }




    // Método listar contenidos
    public List<Contenido> listar(){
        return contenidoRepository.findAll();
    }




    // Método modificar contenido
    public String modificarContenido(Long id, ContenidoDTOputmapping contenidoDTOputmapping) { 
        Optional<Contenido> contenidoOpt = contenidoRepository.findById(id);
        if (contenidoOpt.isPresent()) {
            Contenido contenidoBD = contenidoOpt.get();
            if (contenidoDTOputmapping.getNombre() != null) {
                contenidoBD.setNombre(contenidoDTOputmapping.getNombre());
            }
            if (contenidoDTOputmapping.getDescripcion() != null) {
                contenidoBD.setDescripcion(contenidoDTOputmapping.getDescripcion());
            }
            if (contenidoDTOputmapping.getMaterial() != null) {
                contenidoBD.setMaterial(contenidoDTOputmapping.getMaterial());
            }
            contenidoRepository.save(contenidoBD);
            return "Contenido modificado correctamente.";
        } else {
            return "Contenido no se encuentra para ser modificado.";
        }
    }




    // Método eliminar contenido
    public String eliminarContenido(Long id) {
        Optional<Contenido> optContenido = contenidoRepository.findById(id);
        if (optContenido.isPresent()) {
            contenidoRepository.delete(optContenido.get());
            return "Contenido eliminado correctamente.";
        } else {
            return "Contenido no existe para poder ser eliminado.";
        }
    }



    
}