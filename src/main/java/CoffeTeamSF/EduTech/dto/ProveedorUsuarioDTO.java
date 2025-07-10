package CoffeTeamSF.EduTech.dto;

/*BY RICARDO CUEVAS */


public class ProveedorUsuarioDTO {

    private String id;             
    private String tipo_servicio;
    private String nombre;         
    private String contacto;
    private Long usuarioId;
    private String usuarioNombre;

    public ProveedorUsuarioDTO() {}

    public ProveedorUsuarioDTO(String id, String tipo_servicio, String nombre, String contacto, Long usuarioId, String usuarioNombre) {
        this.id = id;
        this.tipo_servicio = tipo_servicio;
        this.nombre = nombre;
        this.contacto = contacto;
        this.usuarioId = usuarioId;
        this.usuarioNombre = usuarioNombre;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo_servicio() {
        return tipo_servicio;
    }

    public void setTipo_servicio(String tipo_servicio) {
        this.tipo_servicio = tipo_servicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getUsuarioNombre() {
        return usuarioNombre;
    }

    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }
}
