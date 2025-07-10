package CoffeTeamSF.EduTech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CoffeTeamSF.EduTech.model.Cupon;
import CoffeTeamSF.EduTech.model.FormaPago;
import CoffeTeamSF.EduTech.repository.CuponRepository;
import CoffeTeamSF.EduTech.repository.FormaPagoRepository;



//
//**************DESARROLLADO POR RICARDO CUEVAS**************************+ */
//

@Service
public class FormaPagoService {
    @Autowired
    private CuponRepository cuponRepository;
    @Autowired
    private FormaPagoRepository formaPagoRepository;


    public String almacenar(FormaPago formaPago){
        FormaPago validacion = formaPagoRepository.findByDescripcion(formaPago.getDescripcion());
        if (validacion == null){
            formaPagoRepository.save(formaPago);
            return "Forma de pago ingresada";
        }else{
            return "Forma de pago " + formaPago.getDescripcion() + " ya existe";
        }
    }


    //get de todos las Forma pago
    public List<FormaPago> listar(){
        return formaPagoRepository.findAll();
    }


    //get de formas de pago por coincidencia
    public List<FormaPago> buscar(String descripcion){
        return formaPagoRepository.findByDescripcionContaining(descripcion);
    }


    public String agregarCupon(String id, String codigo ){
        if(!formaPagoRepository.existsById(id)){
            return "La forma de pago no existe";
        }else if(!cuponRepository.existsById(codigo)){
            return "Cupon no encontrado";
        }else {
            FormaPago formaPago = formaPagoRepository.findById(id).get();
            Cupon cupon = cuponRepository.findById(codigo).get();
            cupon.setFormaPago(formaPago);
            cuponRepository.save(cupon);
            return "El cupon " + cupon.getCodigo() + " ha sido agregado con exito";
        }
    }


    public String eliminarFormaPago(String id){
        FormaPago formaPago = formaPagoRepository.findById(id).orElse(null);
        if (formaPago != null){
            formaPagoRepository.delete(formaPago);
            return "La forma de pago ya no existe";
        }else{
            return "La forma de pago no existe previamente";
        }
    }



}