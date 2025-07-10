package CoffeTeamSF.EduTech.model;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*CLASE USUARIO
 * BY:NICOL SAAVEDRA
 */
@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String correo;
    private String contrasena;

    /*relacion con tipo de usuario
     * by Nicol Saavedra
     */
    @ManyToOne
    @JoinColumn(name = "idTipoUsuario")
    @JsonBackReference(value = "usuario-tipousuario")
    private TipoUsuario tipoUsuario;   

    /*relación con Inscripcion
     * By Nicol Saavedra
     */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Inscripcion> inscripciones;

    //Relación con Incidencias (Made by Vicente Aravena)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Incidencia> incidencias;


    //Relación con Resenas (Made by Vicente Aravena)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Resena> resenas;

    /*Relacion con EvaluacionUsuario
     * by Nicol Saavedra
     */
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<EvaluacionUsuario> evaluacionUsuarios = new ArrayList<>();
    
    

    public void agregarInscripcion(Inscripcion inscripcion) { 
        this.inscripciones.add(inscripcion);                  
        inscripcion.setUsuario(this);
    }

    public Usuario(){
        this.nombre = "";
        this.apellido= "";
        this.correo = "";
        this.contrasena = "";
        this.inscripciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public List<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }

    public List<Resena> getResenas() {
        return resenas;
    }

    public void setResenas(List<Resena> resenas) {
        this.resenas = resenas;
    }

    public List<EvaluacionUsuario> getEvaluacionUsuarios() {
        return evaluacionUsuarios;
    }

    public void setEvaluacionUsuarios(List<EvaluacionUsuario> evaluacionUsuarios) {
        this.evaluacionUsuarios = evaluacionUsuarios;
    }

}









