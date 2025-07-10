package CoffeTeamSF.EduTech.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import CoffeTeamSF.EduTech.dto.CalificacionDTO;
import CoffeTeamSF.EduTech.dto.EvaluacionDTO;
import CoffeTeamSF.EduTech.service.EvaluacionService;

/*CONTROLLER DE EVALUACION
 * BY NICOL SAAVEDRA
 */
@RestController
@RequestMapping("/evaluaciones")
public class EvaluacionController {
    @Autowired
    private EvaluacionService evaluacionService;

    /*METODO QUE CREA EVALUACIONES
     * BY NICOL SAAVEDRA
     */
    @PostMapping
    public String almacenar(@RequestBody EvaluacionDTO evaluacionDTO){ 
        return evaluacionService.almacenar(evaluacionDTO);
    }

    /*METODO QUE LISTA EVALUACIONES 
     * BY NICOL SAAVEDRA
    */
    @GetMapping
    public List<EvaluacionDTO> listar(){
        return evaluacionService.listarEvaluaciones();
    }

    /*METODO QUE ASIGNA EVALUACIONES A UN CURSO
     * BY NICOL SAAVEDRA
     */
    @PostMapping ("/{evaluacionId}/asignar-curso/{sigla}")
    public String asignarCursoEvaluacion(
        @PathVariable String evaluacionId,
        @PathVariable String sigla){
            return evaluacionService.asignarCursoEvaluacion(evaluacionId, sigla);
        }
    
    /*metodo que asigna una evaluacion a los usuarios de un curso 
     *by Nicol Saavedra
    */
    @PostMapping("/{evaluacionId}/asignar-alumnos-curso/{sigla}")
    public ResponseEntity<?> asignarEvaluacionAAlumnosDelCurso(@PathVariable String evaluacionId) {
    try {
        String resultado = evaluacionService.asignarEvaluacionAAlumnosDelCurso(evaluacionId);
        return ResponseEntity.ok(Map.of("mensaje", resultado));
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    /*metodo que asigna una calificacion a una evaluacion
     * by Nicol Saavedra
     */
    @PutMapping("/calificar-alumno")
    public String calificarAlumno(@RequestBody CalificacionDTO calificacionDTO) {
        return evaluacionService.asignarCalificacionAlumno(
            calificacionDTO.getEvaluacionId(), 
            calificacionDTO.getAlumnoId(), 
            calificacionDTO.getCalificacion()
        );
    }
}


