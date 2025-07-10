package CoffeTeamSF.EduTech.dto;



//Made by Vicente Aravena
//Tercer DTO para listar resenas, sirve para listarlas de otra forma cuando se le hace get a otras entidades en postman


public class ResenaListarDTO3 {
    private String idResena;
    private String nombreUsuario;
    private String curso;      // Solo el nombre del curso
    private String comentario;
    private int calificacion;
    private String fecha;

    public ResenaListarDTO3() {}

    public ResenaListarDTO3(
        String idResena,
        String nombreUsuario,
        String curso,
        String comentario,
        int calificacion,
        String fecha
    ) {
        this.idResena = idResena;
        this.nombreUsuario = nombreUsuario;
        this.curso = curso;
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.fecha = fecha;
    }

    public String getIdResena() {
        return idResena;
    }

    public void setIdResena(String idResena) {
        this.idResena = idResena;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
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
}
