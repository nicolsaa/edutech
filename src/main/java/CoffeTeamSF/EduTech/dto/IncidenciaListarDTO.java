package CoffeTeamSF.EduTech.dto;



//Made by Vicente Aravena
//DTO Creado sólo para el método listarIncidencias


public class IncidenciaListarDTO {
    private Long id;
    private Long idUsuario;
    private String nombreUsuario;
    private String descripcion;
    private String estado;
    private String fechaIncidencia;
    private String fechaResolucion;

    public IncidenciaListarDTO() {
    }

    public IncidenciaListarDTO(Long id, Long idUsuario, String nombreUsuario, String descripcion, String estado, String fechaIncidencia, String fechaResolucion) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaIncidencia = fechaIncidencia;
        this.fechaResolucion = fechaResolucion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
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

    public String getFechaIncidencia() {
        return fechaIncidencia;
    }

    public void setFechaIncidencia(String fechaIncidencia) {
        this.fechaIncidencia = fechaIncidencia;
    }

    public String getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(String fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    









    
}
