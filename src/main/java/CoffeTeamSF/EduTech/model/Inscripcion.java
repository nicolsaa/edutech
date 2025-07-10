package CoffeTeamSF.EduTech.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;


/*CLASE INSCRIPCION 
 * * BY NICOL SAAVEDRA
 */
@Entity
public class Inscripcion {
    @Id
    @Column(name = "id_inscripcion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    /*RELACION CON USUARIO
     * BY NICOL SAAVEDRA
    */
    @ManyToOne (fetch = FetchType.LAZY) 
    @JoinColumn(name = "id_usuario")
    @JsonBackReference(value = "inscripcion-usuario")
    private Usuario usuario;



    /*RELACION CON CURSO
     * BY NICOL SAAVEDRA
    */
    @ManyToOne
    @JoinColumn(name = "id_curso")
    @JsonBackReference
    private Curso curso;
    private LocalDate fechaInscripcion;


    /*Relacion forma de pago by Ricardo Cuevas*/
    @OneToOne
    @JoinColumn(name = "id_forma_pago")
    @JsonManagedReference
    private FormaPago formaPago;


    public Inscripcion() {
        this.fechaInscripcion = LocalDate.now();
    }

    public Inscripcion(Long id, Usuario usuario, Curso curso, LocalDate fechaInscripcion) {
        this.id = id;
        this.usuario = usuario;
        this.curso = curso;
        this.fechaInscripcion = fechaInscripcion;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

}
