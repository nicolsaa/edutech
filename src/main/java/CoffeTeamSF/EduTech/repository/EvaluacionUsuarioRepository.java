package CoffeTeamSF.EduTech.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import CoffeTeamSF.EduTech.model.Evaluacion;
import CoffeTeamSF.EduTech.model.EvaluacionUsuario;
import CoffeTeamSF.EduTech.model.Usuario;

public interface EvaluacionUsuarioRepository extends JpaRepository <EvaluacionUsuario, String> {

    boolean existsByUsuarioAndEvaluacion(Usuario usuario, Evaluacion evaluacion);

    Optional<EvaluacionUsuario> findByEvaluacionIdAndUsuarioId(String evaluacionId, Long usuarioId);
}
