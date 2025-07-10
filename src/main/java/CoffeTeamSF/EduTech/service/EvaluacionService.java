package CoffeTeamSF.EduTech.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import CoffeTeamSF.EduTech.dto.EvaluacionDTO;
import CoffeTeamSF.EduTech.model.Curso;
import CoffeTeamSF.EduTech.model.Evaluacion;
import CoffeTeamSF.EduTech.model.EvaluacionUsuario;
import CoffeTeamSF.EduTech.model.TipoUsuario;
import CoffeTeamSF.EduTech.model.Usuario;
import CoffeTeamSF.EduTech.repository.CursoRepository;
import CoffeTeamSF.EduTech.repository.EvaluacionRepository;
import CoffeTeamSF.EduTech.repository.EvaluacionUsuarioRepository;
import CoffeTeamSF.EduTech.repository.TipoUsuarioRepository;
import CoffeTeamSF.EduTech.repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class EvaluacionService {
    @Autowired
    private EvaluacionRepository evaluacionRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    private EvaluacionUsuarioRepository evaluacionUsuarioRepository;


    /*METODO QUE CREA UNA EVALUACION 
     * BY NICOL SAAVEDRA
    */
    public String almacenar(EvaluacionDTO evaluacionDTO) {
        if (evaluacionRepository.findByTitulo(evaluacionDTO.getTitulo())== null) {
            Evaluacion evaluacion = new Evaluacion();
            evaluacion.setDescripcion(evaluacionDTO.getDescripcion());
            evaluacion.setId(evaluacionDTO.getId());
            evaluacion.setTitulo(evaluacionDTO.getTitulo());
            evaluacionRepository.save(evaluacion);
            return "Evaluacion registrada con exito";
        }else{
            return "la evaluacion con titulo" + evaluacionDTO.getTitulo() + "ya existe";
        }
    }

    /*METODO QUE LISTA LAS EVALUACIONES
     * BY NICOL SAAVEDRA
     */
    public List<EvaluacionDTO> listarEvaluaciones() {
        return evaluacionRepository.findAll().stream().map(e -> new EvaluacionDTO(e.getId(), e.getTitulo(), e.getDescripcion()))
        .collect(Collectors.toList());
    }


    /*METODO QUE ASIGNA UNA EVALUACION A UN CURSO
     * BY NICOL SAAVEDRA
     */
    public String asignarCursoEvaluacion(String evaluacionId, String sigla){
        Optional <Evaluacion> evaluacionOpt = evaluacionRepository.findById(evaluacionId);
        if (!evaluacionOpt.isPresent()){
            return "Evaluacion no encontrada";
        }
        Optional <Curso> cursoOpt = cursoRepository.findById(sigla);
        if (!cursoOpt.isPresent()){
            return "curso no encontrado";
        }
        Evaluacion evaluacion = evaluacionOpt.get();
        evaluacion.setCurso(cursoOpt.get());
        evaluacionRepository.save(evaluacion);
        return "evaluacion asignada con exito";
    }

    /*metodo que asigna una evaluacion a todos los ususarios tipo alumno de un curso
     * by Nicol Saavedra
     */
    @Transactional
    public String asignarEvaluacionAAlumnosDelCurso(String evaluacionId){
        Optional <Evaluacion> evaluacionOpt = evaluacionRepository.findById(evaluacionId);
        if(!evaluacionOpt.isPresent()){
            return "Evaluacion no encontrada";
        }
        Evaluacion evaluacion = evaluacionOpt.get();
        
        if(evaluacion.getCurso() == null){
            return "la evaluacion no esta asignada a ningun curso";
        }

        Optional<TipoUsuario> tipoAlumnoOpt = Optional.ofNullable(tipoUsuarioRepository.findByNombre("alumno"));
        
        if(!tipoAlumnoOpt.isPresent()){
            return "Alumno no encontrado";
        }

        List <Usuario> alumnos = usuarioRepository.findByInscripcionesCursoAndTipoUsuario(evaluacion.getCurso(), tipoAlumnoOpt);

        if(evaluacion.getCurso().getInscripciones().isEmpty()) {
            return "El curso no tiene alumnos inscritos";
        }
        int asignaciones = 0;
        for(Usuario alumno : alumnos) {
        if(!evaluacionUsuarioRepository.existsByUsuarioAndEvaluacion(alumno, evaluacion)) {
            EvaluacionUsuario evaluacionUsuario = new EvaluacionUsuario();
            evaluacionUsuario.setEvaluacion(evaluacion);
            evaluacionUsuario.setUsuario(alumno);
            evaluacionUsuario.setFechaAsignacion(LocalDateTime.now());
            
            evaluacionUsuarioRepository.save(evaluacionUsuario);
            asignaciones++;
        }
    }
    return "Evaluación asignada a " + asignaciones + " alumnos del curso " + evaluacion.getCurso().getNombre();
    }

    /*metodo para Asignar Calificación a un Alumno Específico
     * by Nicol Saavedra
     */
    @Transactional
    public String asignarCalificacionAlumno(String evaluacionId, Long alumnoId, Double calificacion) {
        
        EvaluacionUsuario evaluacionUsuario = evaluacionUsuarioRepository.findByEvaluacionIdAndUsuarioId(evaluacionId, alumnoId)
            .orElseThrow(() -> new RuntimeException("El alumno no tiene asignada esta evaluación"));

        Optional<TipoUsuario> tipoAlumnoOpt = Optional.ofNullable(tipoUsuarioRepository.findByNombre("alumno"));
        
        if(!tipoAlumnoOpt.isPresent()){
            return "Alumno no encontrado";
        }
        if(calificacion == null || calificacion < 0 || calificacion > 7) {
            return "La calificación debe estar entre 0 y 7";
        }
        evaluacionUsuario.setCalificacion(calificacion);
        evaluacionUsuarioRepository.save(evaluacionUsuario);
        return String.format("Calificación" + calificacion + " asignada al alumno ID " + alumnoId + " en la evaluación " + evaluacionId);
        }

}