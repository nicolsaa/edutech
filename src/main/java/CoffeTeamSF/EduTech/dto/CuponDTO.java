package CoffeTeamSF.EduTech.dto;


//Desarrollado por Ricardo Cuevas

public class CuponDTO {
    private String codigo;
    private int porcentaje_descuento;
    private String fecha_expiracion;
    private String id;

    public CuponDTO(){
        this.codigo = "";
        this.porcentaje_descuento = 0;
        this.fecha_expiracion = "";
        this.id = "";
    }

    public CuponDTO(String codigo, int porcentaje_descuento, String fecha_expiracion, String id) {
        this.codigo = codigo;
        this.porcentaje_descuento = porcentaje_descuento;
        this.fecha_expiracion = fecha_expiracion;
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getPorcentaje_descuento() {
        return porcentaje_descuento;
    }

    public void setPorcentaje_descuento(int porcentaje_descuento) {
        this.porcentaje_descuento = porcentaje_descuento;
    }

    public String getFecha_expiracion() {
        return fecha_expiracion;
    }

    public void setFecha_expiracion(String fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

