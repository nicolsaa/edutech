package CoffeTeamSF.EduTech.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import CoffeTeamSF.EduTech.dto.AsignarProfesorDTO;
import CoffeTeamSF.EduTech.dto.CursoAlmacenarDTO;
import CoffeTeamSF.EduTech.dto.CursoDTOputmapping;
import CoffeTeamSF.EduTech.dto.CursoListarDTO;
import CoffeTeamSF.EduTech.dto.EvaluacionDTO;
import CoffeTeamSF.EduTech.dto.EvaluacionUsuarioDTO;
import CoffeTeamSF.EduTech.dto.InscripcionDTO;
import CoffeTeamSF.EduTech.dto.ResenaListarDTO;
import CoffeTeamSF.EduTech.model.Contenido;
import CoffeTeamSF.EduTech.model.Curso;
import CoffeTeamSF.EduTech.model.EvaluacionUsuario;
import CoffeTeamSF.EduTech.model.Resena;
import CoffeTeamSF.EduTech.model.Usuario;
import CoffeTeamSF.EduTech.repository.CursoRepository;
import CoffeTeamSF.EduTech.repository.UsuarioRepository;


//MADE BY VICENTE ARAVENA
@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;



    //Método almacenar curso 
    public ResponseEntity<String> almacenar(CursoAlmacenarDTO dto) {
        // Validar existencia de curso
        if (cursoRepository.findById(dto.getSigla()).isPresent()) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("El curso con sigla " + dto.getSigla() + " ya existe.");
        }
        // Validar existencia del usuario gerente
        if (dto.getIdGerente() == null) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Debe especificar el id del gerente.");
        }
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(dto.getIdGerente());
        if (!usuarioOpt.isPresent()) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("El usuario con id " + dto.getIdGerente() + " no existe.");
        }
        Usuario gerente = usuarioOpt.get();
        // Validar que el usuario tenga un tipo asignado
        if (gerente.getTipoUsuario() == null) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("El usuario con id " + dto.getIdGerente() + " no tiene un tipo de usuario asignado.");
        }
        // Validar que sea GerenteCursos
        if (!"GerenteCursos".equalsIgnoreCase(gerente.getTipoUsuario().getNombre())) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("El usuario con id " + dto.getIdGerente() + " no tiene permisos para crear cursos.");
        }
        // Si todo está bien, crear el curso
        Curso curso = new Curso();
        curso.setSigla(dto.getSigla());
        curso.setNombre(dto.getNombre());
        curso.setDescripcion(dto.getDescripcion());

        cursoRepository.save(curso);

        return ResponseEntity.ok("Curso registrado exitosamente.");
    }


    //Método listar cursos
    public List<CursoListarDTO> listar() {
        List<Curso> cursos = cursoRepository.findAll();
        List<CursoListarDTO> cursosDTO = new ArrayList<>();
        for (Curso curso : cursos) {
            List<Contenido> contenidos = curso.getContenido();

            /*insercion de incripcionDTO by Nicol Saavedra-------------------- */
            List<InscripcionDTO> inscripciones = 
                curso.getInscripciones().stream()
                .map(inscripcion -> 
                    new InscripcionDTO(inscripcion.getUsuario().getNombre(), inscripcion.getFechaInscripcion()))
                    .collect(Collectors.toList()); 

            /*insercion de EvaluacionDTO by Nicol Saavedra--------------------- */
            List<EvaluacionDTO> evaluaciones = 
            curso.getEvaluaciones().stream()
                .map(evaluacion -> 
                    new EvaluacionDTO(evaluacion.getId(), evaluacion.getTitulo(), evaluacion.getDescripcion(), 
                        mapEvaluacionUsuarios(evaluacion.getEvaluacionUsuarios())                    
                    )).collect(Collectors.toList());

            List<ResenaListarDTO> resenasDTO = new ArrayList<>();
            if (curso.getResenas() != null) {
                for (Resena resena : curso.getResenas()) {
                    resenasDTO.add(new ResenaListarDTO(
                        String.valueOf(resena.getidResena()),
                        resena.getComentario(),
                        resena.getCalificacion(),
                        resena.getFecha() != null ? resena.getFecha().toString() : null,
                        resena.getUsuario() != null ? resena.getUsuario().getNombre() : null
                    ));
                }
            }
            CursoListarDTO dto = new CursoListarDTO(
                curso.getSigla(),
                curso.getNombre(),
                curso.getDescripcion(),
                curso.getProfesor(), 
                contenidos,
                inscripciones,
                evaluaciones,
                resenasDTO
            );
            cursosDTO.add(dto);
        }
        return cursosDTO;
    }

    /*metodo que trae la lista de EvaluacionUsuarioDTO
     * by Nicol Saavedra
     */
    private List<EvaluacionUsuarioDTO> mapEvaluacionUsuarios(List<EvaluacionUsuario> evaluacionUsuarios) {
        return evaluacionUsuarios.stream().map(
            usuario -> new EvaluacionUsuarioDTO(usuario.getId(), usuario.getUsuario().getNombre(), usuario.getCalificacion(), usuario.getFechaAsignacion())
        ).collect(Collectors.toList());
    }


    //Método modificar curso
    public String modificarCurso(String sigla, CursoDTOputmapping cursoDTOputmapping) {
        Optional<Curso> cursoOpt = cursoRepository.findById(sigla);
        if (cursoOpt.isPresent()) {
            Curso cursoBD = cursoOpt.get();
            // Solo se actualizan los campos del body
            if (cursoDTOputmapping.getNombre() != null) {
                cursoBD.setNombre(cursoDTOputmapping.getNombre());
            }
            if (cursoDTOputmapping.getDescripcion() != null) {
                cursoBD.setDescripcion(cursoDTOputmapping.getDescripcion());
            }
            cursoRepository.save(cursoBD);
            return "Curso modificado correctamente.";
        } else {
            return "Curso no se encuentra para ser modificado.";
        }
    }



    //Método eliminar (version 3): (Made by Vicente Aravena)
    public String eliminarCurso(String sigla) {
        Optional<Curso> optionalCurso = cursoRepository.findById(sigla);
        if (optionalCurso.isPresent()) {
            cursoRepository.delete(optionalCurso.get());
            return "Curso eliminado correctamente.";
        } else {
            return "Curso no existe para poder ser eliminado.";
        }
    }


    //Método asignar profesor a curso (Made by Vicente Aravena)
    public ResponseEntity<String> asignarProfesorACurso(AsignarProfesorDTO dto) {
        // 1) Validar existencia del gerente
        Optional<Usuario> gerenteOpt = usuarioRepository.findById(dto.getIdGerente());
        if (!gerenteOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("ID no existe en el sistema.");
        }
        Usuario gerente = gerenteOpt.get();
        if (gerente.getTipoUsuario() == null || 
            !gerente.getTipoUsuario().getNombre().equalsIgnoreCase("GerenteCursos")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("ID proporcionado no posee permisos.");
        }
        // 2) Validar existencia del curso
        Optional<Curso> cursoOpt = cursoRepository.findById(dto.getSiglaCurso());
        if (!cursoOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Curso no existe en el sistema.");
        }
        Curso curso = cursoOpt.get();
        // 3) Validar que el curso no tenga profesor asignado
        if (curso.getProfesor() != null && !curso.getProfesor().isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                .body("El curso ya tiene un profesor asignado.");
        }
        // 4) Validar existencia del profesor
        Optional<Usuario> profesorOpt = usuarioRepository.findById(dto.getProfesor_a_asignar());
        if (!profesorOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("ID de profesor no existe.");
        }
        Usuario profesor = profesorOpt.get();
        if (profesor.getTipoUsuario() == null ||
            !profesor.getTipoUsuario().getNombre().equalsIgnoreCase("Profesor")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("ID de profesor incorrecto.");
        }
        // 5) Concatenar nombre + apellido
        String nombreCompleto = profesor.getNombre() + " " + profesor.getApellido();
        curso.setProfesor(nombreCompleto);

        cursoRepository.save(curso);

        return ResponseEntity.ok("Profesor asignado correctamente al curso.");
    }

}




