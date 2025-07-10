package CoffeTeamSF.EduTech.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

//Made By Vicente Aravena
//DTO específico para método listar resena

@JsonPropertyOrder({
    "idResena",
    "nombreUsuario",
    "comentario",
    "calificacion",
    "fecha"
})

public class ResenaListarDTO {
    private String idResena;
    private String comentario;
    private int calificacion;
    private String fecha;
    private String nombreUsuario;

    public ResenaListarDTO() {
    }

    public ResenaListarDTO(String idResena, String comentario, int calificacion, String fecha, String nombreUsuario) {
        this.idResena = idResena;
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.fecha = fecha;
        this.nombreUsuario = nombreUsuario;
    }


    public String getidResena() {
        return idResena;
    }

    public void setidResena(String idResena) {
        this.idResena = idResena;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    



    
}
