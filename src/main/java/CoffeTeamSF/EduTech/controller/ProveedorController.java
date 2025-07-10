package CoffeTeamSF.EduTech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CoffeTeamSF.EduTech.model.Proveedor;
import CoffeTeamSF.EduTech.service.ProveedorService;

//
//**************DESARROLLADO POR RICARDO CUEVAS**************************+ */
//


@RestController
@RequestMapping("/Proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @PostMapping 
    public String almacenar(@RequestBody Proveedor proveedor){ 
        return proveedorService.almacenar(proveedor);
    }

    @GetMapping
    public List<Proveedor> listar(){
        return proveedorService.listar();
    }
}
