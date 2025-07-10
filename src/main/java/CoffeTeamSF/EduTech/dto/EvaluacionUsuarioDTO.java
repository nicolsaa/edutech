package CoffeTeamSF.EduTech.dto;

import java.time.LocalDateTime;

/*EvaluacionUsuarioDTO
 * by Nicol Saavedra
 */
public class EvaluacionUsuarioDTO {
    private String nombre;
    private Double calificacion;
    private LocalDateTime fechaAsignacion;
    



    public EvaluacionUsuarioDTO() {
    }


    public EvaluacionUsuarioDTO(String nombre, Double calificacion, LocalDateTime fechaAsignacion) {
        this.nombre = nombre;
        this.calificacion = calificacion;
        this.fechaAsignacion = fechaAsignacion;
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
