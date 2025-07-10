package CoffeTeamSF.EduTech.dto;

import java.time.LocalDateTime;

/*EvaluacionUsuarioDTO
 * by Nicol Saavedra
 */
public class EvaluacionUsuarioDTO {
    private String id;
    private String nombre;
    private Double calificacion;
    private LocalDateTime fechaAsignacion;
    
    public EvaluacionUsuarioDTO(String id, String nombre, Double calificacion, LocalDateTime fechaAsignacion) {
        this.id = id;
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.fechaAsignacion = fechaAsignacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }

    public LocalDateTime getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(LocalDateTime fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }


}
