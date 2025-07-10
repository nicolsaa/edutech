package CoffeTeamSF.EduTech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;



//
//**************DESARROLLADO POR RICARDO CUEVAS**************************+ */
//


@Entity
public class Reporte {
    @Id
    private String id;
    private String nombre;
    private String descripcion;


    public Reporte(){
        this.id = "";
        this.nombre = "";
        this.descripcion = "";
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    

}
