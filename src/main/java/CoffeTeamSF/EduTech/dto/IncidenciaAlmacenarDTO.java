package CoffeTeamSF.EduTech.dto;




//Made by Vicente Aravena

//DTO creado para el método "Almacenar Incidencia" 29/6

public class IncidenciaAlmacenarDTO {
    private String descripcion;
    private Long idUsuario;


    //Constructores:
    public IncidenciaAlmacenarDTO() {
    }

    public IncidenciaAlmacenarDTO(String descripcion, Long idUsuario) {
        this.descripcion = descripcion;
        this.idUsuario = idUsuario;
    }


    //Getter and Setter:
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    



}
