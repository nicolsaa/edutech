package CoffeTeamSF.EduTech.repository;


//
//**************DESARROLLADO POR RICARDO CUEVAS**************************+ */
//

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import CoffeTeamSF.EduTech.model.FormaPago;

public interface FormaPagoRepository extends JpaRepository<FormaPago, String>{
    FormaPago findByDescripcion(String descripcion);
    List<FormaPago> findByDescripcionContaining(String descripcion);
}
