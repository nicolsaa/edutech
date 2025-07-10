package CoffeTeamSF.EduTech.dto;


//MADE BY VICENTE ARAVENA

//Este CursoDTO es creado específicamente para el método putmapping modificar Curso.
//Se tuvo que hacer ya que anteriormente se usaba directamente la clase curso model en ese método, y daba problema de deserialización con los Jsonbackreference.

public class CursoDTOputmapping {
    private String nombre;
    private String descripcion;


    public CursoDTOputmapping() {
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
