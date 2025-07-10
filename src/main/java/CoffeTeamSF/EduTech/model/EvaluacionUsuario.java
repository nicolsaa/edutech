package CoffeTeamSF.EduTech.model;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/*entidad relacion evaluacion usuario
 * by Nicol Saavedra
 */
@Entity
@Table(name = "evaluacion_usuario")
public class EvaluacionUsuario {
    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonBackReference(value = "evaluacionUsuario_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "evaluacion_id")
    @JsonBackReference(value = "evaluacionUsuario_evaluacion")
    private Evaluacion evaluacion;

    private Double calificacion;
    private LocalDateTime fechaAsignacion;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
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
