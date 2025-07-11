package CoffeTeamSF.EduTech.service;

import java.util.List;



//
//**************DESARROLLADO POR RICARDO CUEVAS**************************+ */
//

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CoffeTeamSF.EduTech.model.Cupon;
import CoffeTeamSF.EduTech.repository.CuponRepository;



//
//**************DESARROLLADO POR RICARDO CUEVAS**************************+ */
//


@Service
public class CuponService {
    @Autowired
    private CuponRepository cuponRepository;


    public String almacenar(Cupon cupon){
        if(cuponRepository.existsById(cupon.getCodigo())){
            return "El cupon ya existe";
        }else{
            cuponRepository.save(cupon);
            return "Cupon agregado correctamente";
        } 
    }


    public List<Cupon> listar(){
        return cuponRepository.findAll();
    }


    public String eliminarCupon(String codigo) {
        Cupon cupon = cuponRepository.findById(codigo).orElse(null);
        if (cupon != null) {
            cuponRepository.delete(cupon);
            return "Cupon eliminado correctamente.";
        } else {
            return "El Cupon no existe";
        }
    }



}
