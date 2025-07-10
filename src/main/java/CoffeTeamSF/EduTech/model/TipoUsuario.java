package CoffeTeamSF.EduTech.model;


import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/*CLASE TIPOUSUARIO
 * BY NICOL SAAVEDRA
 */
@Entity
public class TipoUsuario {
    @Id
    @Column(name = "id_tipo_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    /*RELACION CON USUARIO
     * bY NICOL SAAVEDRA
    */
    @OneToMany(mappedBy = "tipoUsuario", cascade = CascadeType.ALL)  
    private List<Usuario> usuarios = new ArrayList<>();

    public TipoUsuario(){
        this.nombre = "";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



}
