package CoffeTeamSF.EduTech.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


//MADE BY VICENTE ARAVENA
@Entity
public class Resena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idResena;
    private String comentario;
    private int calificacion;
    private LocalDate fecha;



    //Relación con Usuario (Made by Vicente Aravena)
    @ManyToOne
    @JoinColumn(name = "usuario_id") 
    @JsonBackReference(value = "resena-usuario")
    private Usuario usuario; 




    //Relación con Curso (Made by Vicente Aravena)
    @ManyToOne
    @JoinColumn(name = "curso_sigla")
    @JsonBackReference
    private Curso curso;





    public Resena(){
        this.comentario = "";
        this.calificacion = 0;
        this.fecha = LocalDate.now();
    }


    public Long getidResena() {
        return idResena;
    }

    public void setidResena(Long idResena) {
        this.idResena = idResena;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }


    public Usuario getUsuario() {
        return usuario;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public Curso getCurso() {
        return curso;
    }


    public void setCurso(Curso curso) {
        this.curso = curso;
    }


    



}
