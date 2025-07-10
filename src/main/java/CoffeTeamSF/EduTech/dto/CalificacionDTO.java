package CoffeTeamSF.EduTech.dto;

public class CalificacionDTO {
    private String evaluacionId;
    private Long alumnoId;
    private Double calificacion;


    public CalificacionDTO(String evaluacionId, Long alumnoId, Double calificacion) {
        this.evaluacionId = evaluacionId;
        this.alumnoId = alumnoId;
        this.calificacion = calificacion;
    }

    public CalificacionDTO() {
    }

    public String getEvaluacionId() {
        return evaluacionId;
    }

    public void setEvaluacionId(String evaluacionId) {
        this.evaluacionId = evaluacionId;
    }

    public Long getAlumnoId() {
        return alumnoId;
    }

    public void setAlumnoId(Long alumnoId) {
        this.alumnoId = alumnoId;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Double calificacion) {
        this.calificacion = calificacion;
    }


}
