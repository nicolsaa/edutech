package CoffeTeamSF.EduTech.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


//MADE BY VICENTE ARAVENA
@Entity
public class Incidencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private String estado; 
    private String fecha_incidencia;
    private String fecha_resolucion;




    //Relación con Usuario (Made by Vicente Aravena)
    @ManyToOne
    @JoinColumn(name = "usuario_id") 
    @JsonBackReference(value = "incidencia-usuario")
    private Usuario usuario; 






    public Incidencia(){
        this.descripcion = "";
        this.estado = "";
        this.fecha_incidencia = "";
        this.fecha_resolucion = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_incidencia() {
        return fecha_incidencia;
    }

    public void setFecha_incidencia(String fecha_incidencia) {
        this.fecha_incidencia = fecha_incidencia;
    }

    public String getFecha_resolucion() {
        return fecha_resolucion;
    }

    public void setFecha_resolucion(String fecha_resolucion) {
        this.fecha_resolucion = fecha_resolucion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    


}
