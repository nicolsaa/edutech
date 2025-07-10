package CoffeTeamSF.EduTech.dto;

import java.time.LocalDate;



/*INSCRIPCION DTO
 * BY NICOL SAAVEDRA
 */
public class InscripcionDTO {
    
    
    private Long inscripcion;
    private String sigla;
    private String nombreCurso;
    private LocalDate fechaInscripcion; 
    private String nombreAlumno;
    private Long idUsuario;


    public InscripcionDTO(Long inscripcion, String sigla, String nombreCurso, LocalDate fechaInscripcion,
            String nombreAlumno) {
        this.inscripcion = inscripcion;
        this.sigla = sigla;
        this.nombreCurso = nombreCurso;
        this.fechaInscripcion = fechaInscripcion;
        this.nombreAlumno = nombreAlumno;
    }

    
    public InscripcionDTO(Long inscripcion, String sigla, String nombreCurso, LocalDate fechaInscripcion, Long idUsuario) {
        this.inscripcion = inscripcion;
        this.sigla = sigla;
        this.nombreCurso = nombreCurso;
        this.fechaInscripcion = fechaInscripcion;
        this.idUsuario = idUsuario;
    }

    public InscripcionDTO(String nombre, LocalDate fecha) {
        this.nombreAlumno = nombre;
        this.fechaInscripcion = fecha;
    }

    public InscripcionDTO(Long idUsuario, String sigla) {
        this.idUsuario = idUsuario;
        this.sigla = sigla;
    }

    

    public InscripcionDTO() {
    }


    public Long getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Long inscripcion) {
        this.inscripcion = inscripcion;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    
}
