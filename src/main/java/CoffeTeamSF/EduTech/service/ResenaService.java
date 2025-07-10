package CoffeTeamSF.EduTech.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import CoffeTeamSF.EduTech.dto.ResenaAlmacenarDTO;
import CoffeTeamSF.EduTech.dto.ResenaListarDTO3;
import CoffeTeamSF.EduTech.model.Curso;
import CoffeTeamSF.EduTech.model.Resena;
import CoffeTeamSF.EduTech.model.Usuario;
import CoffeTeamSF.EduTech.repository.CursoRepository;
import CoffeTeamSF.EduTech.repository.ResenaRepository;
import CoffeTeamSF.EduTech.repository.UsuarioRepository;


//MADE BY VICENTE ARAVENA
@Service
public class ResenaService {
    @Autowired
    private ResenaRepository resenaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;



    //Método almacenar usando DTO (solo deja almacenar resenas si es que existe un Usuario y un Curso)
    public ResponseEntity<String> almacenarResena(ResenaAlmacenarDTO resenaDTO) {
        // Verificar existencia de un Usuario
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(resenaDTO.getIdUsuario());
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Usuario no encontrado. No se puede almacenar reseña.");
        }
        // Verificar existencia de un Curso por nombre
        Optional<Curso> cursoOpt = cursoRepository.findByNombre(resenaDTO.getNombreCurso());
        if (cursoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Curso no encontrado. No se puede almacenar reseña.");
        }
        //Crear la Resena
        Resena resena = new Resena();
        resena.setComentario(resenaDTO.getComentario());
        resena.setCalificacion(resenaDTO.getCalificacion());
        resena.setFecha(LocalDate.now());
        resena.setUsuario(usuarioOpt.get());
        resena.setCurso(cursoOpt.get());
        
        // Guardar la reseña
        resenaRepository.save(resena);
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Reseña de usuario id " + resenaDTO.getIdUsuario() +
                " registrada exitosamente en curso " + resenaDTO.getNombreCurso() + ".");
    }






    //Método listar 
    public List<ResenaListarDTO3> listarResenas() {
        List<Resena> resenas = resenaRepository.findAll();
        List<ResenaListarDTO3> resenasDTO = new ArrayList<>();
        for (Resena resena : resenas) {
            resenasDTO.add(
                new ResenaListarDTO3(
                    String.valueOf(resena.getidResena()),
                    resena.getUsuario() != null ? resena.getUsuario().getNombre() : null,
                    resena.getCurso() != null ? resena.getCurso().getNombre() : null, // ahora solo el nombre
                    resena.getComentario(),
                    resena.getCalificacion(),
                    resena.getFecha() != null ? resena.getFecha().toString() : null
                )
            );
        }
        return resenasDTO;
    }








}
