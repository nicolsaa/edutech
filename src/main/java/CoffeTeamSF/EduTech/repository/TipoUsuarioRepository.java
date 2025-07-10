package CoffeTeamSF.EduTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import CoffeTeamSF.EduTech.model.TipoUsuario;

public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Long>{
    
    /*METODO CUSTOM QUE BUSCA EL TIPO DE USUSARIO POR NOMBRE 
     * BY: NICOL SAAVEDRA
    */
    TipoUsuario findByNombre(String nombre);

    /*METODO QUE ELIMINA TIPO DE USUARIO POR NOMBRE
     * BY NICOL SAAVEDRA
     */
    void deleteByNombre(String nombre);
}
