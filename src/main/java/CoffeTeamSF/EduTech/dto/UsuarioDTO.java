package CoffeTeamSF.EduTech.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import CoffeTeamSF.EduTech.model.Incidencia;


/*DTO DE USUARIO
 * BY: NICOL SAAVEDRA
 */
//JsonPropertyOrder made by Vicente Aravena (permite manejar el orden en el que aparecen los atributos del usuario cuando se hace Get Usuarios en postman)
@JsonPropertyOrder({
    "id",
    "nombre",
    "apellido",
    "correo",
    "tipoUsuario",
    "inscripciones",
	"resenas",
    "incidencias"
})

public class UsuarioDTO {
    private Long id;
    private String nombre;
    private String apellido;
    private String Correo;
    private String tipoUsuario;
    private List<InscripcionDTO> inscripciones;
    private List<Incidencia> incidencias; // CAMBIADO AQUÍ
    private List<ResenaListarDTO2> resenas;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nombre, String apellido, String correo, String tipoUsuario,
                    List<InscripcionDTO> inscripciones,
                    List<Incidencia> incidencias,
                    List<ResenaListarDTO2> resenas) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.Correo = correo;
        this.tipoUsuario = tipoUsuario;
        this.inscripciones = inscripciones;
        this.incidencias = incidencias;
        this.resenas = resenas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public List<InscripcionDTO> getInscripciones() {
        return inscripciones;
    }

    public void setInscripciones(List<InscripcionDTO> inscripciones) {
        this.inscripciones = inscripciones;
    }

    public List<Incidencia> getIncidencias() { // CAMBIADO AQUÍ
        return incidencias;
    }

    public void setIncidencias(List<Incidencia> incidencias) { // CAMBIADO AQUÍ
        this.incidencias = incidencias;
    }

    public List<ResenaListarDTO2> getResenas() {
        return resenas;
    }

    public void setResenas(List<ResenaListarDTO2> resenas) {
        this.resenas = resenas;
    }


    
}