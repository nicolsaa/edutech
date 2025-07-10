package CoffeTeamSF.EduTech.dto;


//Made By Vicente Aravena
//Segundo DTO para listar resenas, sirve para listarlas de otra forma cuando se le hace get a otras entidades en postman
//DTO específico para usarse en UsuarioService, en método listarUsuarios, se usa dentro este DTO de resenas:

public class ResenaListarDTO2 {
    private String idResena;
    private String nombreCurso;
    private String comentario;
    private int calificacion;
    private String fecha;

    public ResenaListarDTO2() {}

    public ResenaListarDTO2(
        String idResena,
        String nombreCurso,
        String comentario,
        int calificacion,
        String fecha
    ) {
        this.idResena = idResena;
        this.nombreCurso = nombreCurso;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}

