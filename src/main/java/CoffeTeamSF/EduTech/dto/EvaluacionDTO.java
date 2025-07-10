package CoffeTeamSF.EduTech.dto;

import java.util.List;

/*EvaluacionDTO
 * by Nicol Saavedra
 */
public class EvaluacionDTO {
    
    private String id;
    private String titulo;
    private String descripcion;
    private List<EvaluacionUsuarioDTO> evaluacionesUsuario;

    public EvaluacionDTO(String id, String titulo, String descripcion) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
    }

    public EvaluacionDTO(String id, String titulo, String descripcion, List<EvaluacionUsuarioDTO> evaluacionesUsuario) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.evaluacionesUsuario = evaluacionesUsuario;
    }

    

    public EvaluacionDTO() {
    }

    public List<EvaluacionUsuarioDTO> getEvaluacionesUsuario() {
        return evaluacionesUsuario;
    }

    public void setEvaluacionesUsuario(List<EvaluacionUsuarioDTO> evaluacionesUsuario) {
        this.evaluacionesUsuario = evaluacionesUsuario;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
