package CoffeTeamSF.EduTech.dto;



//Made By Vicente Aravena
//DTO específico para poder asignar profesor a un curso

public class AsignarProfesorDTO {

    private Long idGerente;
    private String siglaCurso;
    private Long profesor_a_asignar;

    public AsignarProfesorDTO() {
    }

    public Long getIdGerente() {
        return idGerente;
    }

    public void setIdGerente(Long idGerente) {
        this.idGerente = idGerente;
    }

    public String getSiglaCurso() {
        return siglaCurso;
    }

    public void setSiglaCurso(String siglaCurso) {
        this.siglaCurso = siglaCurso;
    }

    public Long getProfesor_a_asignar() {
        return profesor_a_asignar;
    }

    public void setProfesor_a_asignar(Long profesor_a_asignar) {
        this.profesor_a_asignar = profesor_a_asignar;
    }


    
}
