package CoffeTeamSF.EduTech.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CoffeTeamSF.EduTech.model.FormaPago;
import CoffeTeamSF.EduTech.service.FormaPagoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//
//**************DESARROLLADO POR RICARDO CUEVAS**************************+ */
//

@RestController
@RequestMapping("/formasdepago")
public class FormaPagoController {
    @Autowired 
    private FormaPagoService formaPagoService;

    @PostMapping
    public String almacenar(@RequestBody FormaPago formaPago){
        return formaPagoService.almacenar(formaPago);
    }

    @GetMapping
    public List<FormaPago>  listar(){
        return formaPagoService.listar();
    }
    
    @PostMapping("/agregarCupon/{id}/{codigo}")
    public String agregarCupon(@PathVariable String id, @PathVariable String codigo){
            return formaPagoService.agregarCupon(id, codigo);
        }

    @DeleteMapping("/{id}")
    public String eliminarFormaPago(@PathVariable String id){
        return formaPagoService.eliminarFormaPago(id);
    }

    @GetMapping("/{descripcion}")
    public List<FormaPago> buscar(@PathVariable String descripcion){
        return formaPagoService.buscar(descripcion);
    }
    
}

