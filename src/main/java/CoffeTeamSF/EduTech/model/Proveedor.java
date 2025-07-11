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
public class Proveedor {
    @Id
    private String id;
    private String tipo_servicio;
    private String nombre;
    private String contacto;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    @JsonBackReference(value = "proveedor-usuario")
    private Usuario usuario;

    public Proveedor(){
        this.id = "";
        this.tipo_servicio = "";
        this.nombre = "";
        this.contacto = "";
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    

}
