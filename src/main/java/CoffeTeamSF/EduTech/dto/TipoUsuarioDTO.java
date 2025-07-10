package CoffeTeamSF.EduTech.dto;


/*DTO TIPO DE USUARIO
 * BY NICOL SAAVEDRA
 */
public class TipoUsuarioDTO {
    private Long idUsuario;
    private Long idTipoUsuario;
	private String nombre;
	
    
	public TipoUsuarioDTO() {
	}

	public TipoUsuarioDTO(Long idUsuario, Long idTipoUsuario, String nombre) {
		this.idUsuario = idUsuario;
		this.idTipoUsuario = idTipoUsuario;
		this.nombre = nombre;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}
	public Long getIdTipoUsuario() {
		return idTipoUsuario;
	}
	public void setIdTipoUsuario(Long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
