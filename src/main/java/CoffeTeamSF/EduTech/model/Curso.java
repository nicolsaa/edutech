package CoffeTeamSF.EduTech.model;


import java.util.ArrayList;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


//Made by Nicol y Vicente
@Entity
public class Curso {
    @Id
    private String sigla;
    private String nombre;
    private String descripcion;
    private String profesor;

    // Relación con Contenido
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Contenido> contenido;

    /* RELACIÓN CON INSCRIPCIÓN */
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Inscripcion> inscripciones;

    /* RELACIÓN CON EVALUACION */
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Evaluacion> evaluaciones;

    // Relación con Resena
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Resena> resenas = new ArrayList<>();


    
    public Curso() {
        this.sigla = "";
        this.nombre = "";
        this.descripcion = "";
    }



    // Getters y setters

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public List<Contenido> getContenido() {
        return contenido;
    }

    public void setContenido(List<Contenido> contenido) {
        this.contenido = contenido;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public List<Evaluacion> getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(List<Evaluacion> evaluaciones) {
        this.evaluaciones = evaluaciones;
    }

    public List<Resena> getResenas() {
        return resenas;
    }

    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }
}
