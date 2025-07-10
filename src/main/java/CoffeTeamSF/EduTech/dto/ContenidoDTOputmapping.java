package CoffeTeamSF.EduTech.dto;


//MADE BY VICENTE ARAVENA

//Este ContenidoDTO es creado específicamente para el método putmapping modificar contenido.
//Se tuvo que hacer ya que anteriormente se usaba directamente la clase contenido model en ese método, y daba problema de deserialización con los Jsonbackreference.
public class ContenidoDTOputmapping {

    private String nombre;
    private String descripcion;
    private String material;

    

    
    public ContenidoDTOputmapping() {
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
    public String getMaterial() {
        return material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }




    


}
