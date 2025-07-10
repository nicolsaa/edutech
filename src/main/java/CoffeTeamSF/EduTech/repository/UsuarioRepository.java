package CoffeTeamSF.EduTech.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import CoffeTeamSF.EduTech.model.Curso;
import CoffeTeamSF.EduTech.model.TipoUsuario;
import CoffeTeamSF.EduTech.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    /*METODO CUSTOM QUE BUSCA POR CORREO
     * BY: NICOL SAAVEDRA
     */
    Usuario findByCorreo(String correo);

    /*METODO CUSTOM QUE BORRA POR NOMBRE
     * BY: NICOL SAAVEDRA
     */
    void deleteByCorreo(String correo);

    /*lista ususarios por tipo especifico 
     * by Nicol Saavedra
     */
    @Query("SELECT u FROM Usuario u WHERE u.tipoUsuario.nombre = :nombreTipo")
    List<Usuario> findByTipoUsuario(@Param("nombreTipo") String nombreTipo);

    
    /*metodo que busca las inscripciones en un curso */
    @Query("SELECT DISTINCT u FROM Usuario u " + "JOIN u.inscripciones i " +
        "WHERE i.curso = :curso AND u.tipoUsuario = :tipoUsuario")
    List<Usuario> findByInscripcionesCursoAndTipoUsuario(
        @Param("curso") Curso curso,
        @Param("tipoUsuario") Optional<TipoUsuario> tipoAlumnoOpt);
}
