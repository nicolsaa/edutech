package CoffeTeamSF.EduTech.dto;



//MADE BY VICENTE ARAVENA

//Este contenidoDTO es para Postmapping, para buscar con la sigla de curso si curso existe primero antes de crear un contenido en el sistema.
//También verifica por idProfesor si es que en postman se está ingresando el id de un Usuario tipousuario "Profesor"
public class ContenidoAlmacenarDTO {
    private String nombre; 
    private String descripcion;
    private String material;
    private String sigla; // id del Curso
    private Long idProfesor;

    public ContenidoAlmacenarDTO(){ 
        this.nombre = "";
        this.descripcion = "";
        this.material = "";
        this.sigla = "";  
    }

    public ContenidoAlmacenarDTO(String nombre, String descripcion, String material, String sigla, Long idProfesor){
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.material = material;
        this.sigla = sigla;
        this.idProfesor = idProfesor;
    }
    

    // Getters y Setters
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public Long getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Long idProfesor) {
        this.idProfesor = idProfesor;
    }
}
