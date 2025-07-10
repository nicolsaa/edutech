package CoffeTeamSF.EduTech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CoffeTeamSF.EduTech.model.Cupon;
import CoffeTeamSF.EduTech.service.CuponService;


//
//**************DESARROLLADO POR RICARDO CUEVAS**************************+ */
//


@RestController
@RequestMapping("/cupones")
public class CuponController {
    @Autowired
    private CuponService cuponService;

    @PostMapping
    public String almacenar(@RequestBody Cupon cupon){
        return cuponService.almacenar(cupon);
    }

    @GetMapping
    public List<Cupon> listar(){
        return cuponService.listar();
    }

    @DeleteMapping("/{codigo}")
    public String eliminarCupon(@PathVariable String codigo){
        return cuponService.eliminarCupon(codigo);
    }

}

