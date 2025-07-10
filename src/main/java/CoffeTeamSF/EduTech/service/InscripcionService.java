package CoffeTeamSF.EduTech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import CoffeTeamSF.EduTech.dto.InscripcionDTO;
import CoffeTeamSF.EduTech.model.Curso;
import CoffeTeamSF.EduTech.model.Inscripcion;
import CoffeTeamSF.EduTech.model.Usuario;
import CoffeTeamSF.EduTech.repository.CursoRepository;
import CoffeTeamSF.EduTech.repository.InscripcionRepository;
import CoffeTeamSF.EduTech.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

/*INSCRIPCION 
  * BY NICOL SAAVEDRA
  */
@Service
public class InscripcionService {
    @Autowired 
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;


    /*METODO QUE INSCRIBE UN TIPO DE USUSARIO A UN CURSO ESPECIFICO
     * BY NICOL SAAVEDRA
     */
    public String inscribirTipoUsuarioACurso(InscripcionDTO inscripcionDTO ){
        if(inscripcionDTO == null || inscripcionDTO.getIdUsuario() == null || inscripcionDTO.getSigla() == null){
            return "datos de inscripcion invalidos";
        }

        Usuario usuario = usuarioRepository.findById(inscripcionDTO.getIdUsuario()).orElseThrow(()-> new EntityNotFoundException("Usuario no encontrado"));

        if (usuario.getTipoUsuario() == null) {
            return "usuario no tiene un tipo asociado";
        }

        Curso curso = cursoRepository.findById(inscripcionDTO.getSigla()).orElseThrow(()-> new EntityNotFoundException("Curso no encontrado"));

        if(inscripcionRepository.existsByUsuarioAndCursoSigla(usuario, curso.getSigla())){
            return "el usuario ya esta registrado en este curso";
        }

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setCurso(curso);
        inscripcion.setUsuario(usuario);
        inscripcion.setFechaInscripcion(LocalDate.now());
        inscripcionRepository.save(inscripcion);

        return "usuario inscrito con exito";
    }

}
