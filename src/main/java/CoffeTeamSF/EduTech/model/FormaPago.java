package CoffeTeamSF.EduTech.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


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


    @OneToOne(mappedBy = "formaPago")
    @JsonBackReference
    private Inscripcion inscripcion;


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

    public Inscripcion getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripcion inscripcion) {
        this.inscripcion = inscripcion;
    }

    

}
