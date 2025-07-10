package CoffeTeamSF.EduTech.dto;


//Made By Vicente Aravena

public class CursoAlmacenarDTO {

    private String sigla;
    private String nombre;
    private String descripcion;
    private Long idGerente; // Nuevo campo solicitado para poder crear un Curso

    public CursoAlmacenarDTO() {
        this.sigla = "";
        this.nombre = "";
        this.descripcion = "";
    }

    public CursoAlmacenarDTO(String sigla, String nombre, String descripcion, Long idGerente) {
        this.sigla = sigla;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idGerente = idGerente;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
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

    public Long getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(Long idGerente) {
        this.idGerente = idGerente;
    }




}
