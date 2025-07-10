package CoffeTeamSF.EduTech.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


//
//**************DESARROLLADO POR RICARDO CUEVAS**************************+ */
//

@Entity
public class FormaPago {
    @Id
    private String id;
    private String descripcion;

    
    //Relación con Cupon
    @OneToMany(mappedBy = "formaPago")
    private List<Cupon> cupons;



    public FormaPago(){
        this.id = "";
        this.descripcion = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Cupon> getCupons() {
        return cupons;
    }

    public void setCupons(List<Cupon> cupons) {
        this.cupons = cupons;
    }

    

}
