package CoffeTeamSF.EduTech.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import CoffeTeamSF.EduTech.model.Inscripcion;
import CoffeTeamSF.EduTech.model.Usuario;

/*REPOSITORY INSCRIPCION
 * BY NICOL SAAVEDRA
 */
@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {


    /*METODOS QUE BUSCA POR USUARIO
     * BY NICOL SAAVEDRA
     */
    Optional<List<Inscripcion>> findByUsuario(Usuario usuario);


}
