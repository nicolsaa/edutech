package CoffeTeamSF.EduTech.model;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


/*Clase Evaluacion
 * by Nicol Saavedra
 */
@Entity
public class Evaluacion {
    @Id
    private String id;
    private String titulo;
    private String descripcion;
    private Double calificacion;

    /*Relacion con curso
     * By Nicol Saavedra
     */
    @ManyToOne
    @JoinColumn(name = "curso_id")
    @JsonBackReference(value = "evaluacion-curso")
    private Curso curso;

    /*Relacion con EvaluacionUsuario 
     * by Nicol Saavedra
     */
    @OneToMany(mappedBy = "evaluacion",cascade = CascadeType.ALL)
    private List<EvaluacionUsuario> evaluacionUsuarios = new ArrayList<>();
    

    public Evaluacion (){
        this.id = "";
        this.titulo = "";
        this.descripcion = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<EvaluacionUsuario> getEvaluacionUsuarios() {
        return evaluacionUsuarios;
    }

    public void setEvaluacionUsuarios(List<EvaluacionUsuario> evaluacionUsuarios) {
        this.evaluacionUsuarios = evaluacionUsuarios;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }
}
