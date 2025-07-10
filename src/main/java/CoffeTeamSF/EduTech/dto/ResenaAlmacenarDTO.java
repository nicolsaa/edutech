package CoffeTeamSF.EduTech.dto;




//Made By Vicente Aravena
//DTO hecho específicamente para método almacenarResena 

public class ResenaAlmacenarDTO {
    private Long idUsuario;
    private String nombreCurso;
    private String comentario;
    private int calificacion;

    public ResenaAlmacenarDTO() {}

    public ResenaAlmacenarDTO(Long idUsuario, String nombreCurso, String comentario, int calificacion) {
        this.idUsuario = idUsuario;
        this.nombreCurso = nombreCurso;
        this.comentario = comentario;
        this.calificacion = calificacion;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
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
}
