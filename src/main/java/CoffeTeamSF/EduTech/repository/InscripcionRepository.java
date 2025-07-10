package CoffeTeamSF.EduTech.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import CoffeTeamSF.EduTech.model.Inscripcion;
import CoffeTeamSF.EduTech.model.Usuario;

/*REPOSITORY INSCRIPCION
 * BY NICOL SAAVEDRA
 */
@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    static final String EXIST_CURSO_AND_USUARIO_QUERY = "SELECT COUNT(i) > 0 FROM Inscripcion i WHERE i.usuario = :usuario AND i.curso.sigla = :sigla";

    /*METODOS QUE BUSCA POR USUARIO
     * BY NICOL SAAVEDRA
     */
    Optional<List<Inscripcion>> findByUsuario(Usuario usuario);

    /*metodo que comprueba si el usuario y el curso existen 
     * by Nicol Saavedra
    */
    @Query(EXIST_CURSO_AND_USUARIO_QUERY)
    boolean existsByUsuarioAndCursoSigla(@Param("usuario") Usuario usuario, @Param("sigla") String sigla);

}
