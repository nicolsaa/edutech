package CoffeTeamSF.EduTech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CoffeTeamSF.EduTech.model.Proveedor;
import CoffeTeamSF.EduTech.repository.ProveedorRepository;


//
//**************DESARROLLADO POR RICARDO CUEVAS**************************+ */
//

@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;



    public String almacenar(Proveedor proveedor){
        if(proveedorRepository.existsById(proveedor.getId())){
            return "EL proveedor ya existe en las bases de datos";
        }else{
            proveedorRepository.save(proveedor);
            return "El proveedor ha sido agregado correctamente";
        } 
    }


    public List<Proveedor> listar(){
        return proveedorRepository.findAll();
    }
}
