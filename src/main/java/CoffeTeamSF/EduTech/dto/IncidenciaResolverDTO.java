package CoffeTeamSF.EduTech.dto;




//Made By Vicente Aravena
//DTO creado sólo para resolver incidencias en Postman (Se pide solo el id de un usuario que sea tipousuario "Soporte" 
//el id de la incidencia existente se solicitará en la URL, por convención de métodos PUT.


public class IncidenciaResolverDTO {
    private Long idUsuario;

    
    public IncidenciaResolverDTO() {
    }

    public IncidenciaResolverDTO(Long idUsuario) {
        this.idUsuario = idUsuario;
    }



    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }
}