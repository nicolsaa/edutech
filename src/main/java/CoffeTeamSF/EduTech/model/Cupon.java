package CoffeTeamSF.EduTech.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

//
//**************DESARROLLADO POR RICARDO CUEVAS**************************+ */
//

@Entity
public class Cupon {
    @Id
    private String codigo;
    private int porcentaje_descuento;
    private String fecha_expiracion;


    //Relación con FormaPago
    @ManyToOne
    @JoinColumn(name = "formmaPago_id")
    @JsonBackReference(value = "cupon-formapago")
    private FormaPago formaPago;


    
    public Cupon(){
        this.codigo = "";
        this.porcentaje_descuento = 0;
        this.fecha_expiracion = ""; 
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

    public FormaPago getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {
        this.formaPago = formaPago;
    }

    
}
