package CoffeTeamSF.EduTech.dto;

import java.time.LocalDate;

/* ************************************* */
/* ************************************* */
/*CREADO POR RICARDO 10-07-2025 */
/* ************************************* */
/* ************************************* */
public class InscripcionFormapagoDTO {
    private Long id;
    private String nombreUsuario;
    private String nombreCurso;
    private LocalDate fechaInscripcion;
    private String descripcionFormaPago;
    private Integer porcentajeDescuento;

    public InscripcionFormapagoDTO(Long id, String nombreUsuario, String nombreCurso, LocalDate fechaInscripcion,String descripcionFormaPago, Integer porcentajeDescuento) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.nombreCurso = nombreCurso;
        this.fechaInscripcion = fechaInscripcion;
        this.descripcionFormaPago = descripcionFormaPago;
        this.porcentajeDescuento = porcentajeDescuento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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

    public String getDescripcionFormaPago() {
        return descripcionFormaPago;
    }

    public void setDescripcionFormaPago(String descripcionFormaPago) {
        this.descripcionFormaPago = descripcionFormaPago;
    }

    public Integer getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(Integer porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }
}
