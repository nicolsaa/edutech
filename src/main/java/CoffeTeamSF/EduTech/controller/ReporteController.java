package CoffeTeamSF.EduTech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import CoffeTeamSF.EduTech.model.Reporte;
import CoffeTeamSF.EduTech.service.ReporteService;

//
//**************DESARROLLADO POR RICARDO CUEVAS**************************+ */
//

public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @PostMapping 
    public String almacenar(@RequestBody Reporte reporte){ 
        return reporteService.almacenar(reporte);
    }

    @GetMapping
    public List<Reporte> listar(){
        return reporteService.listar();
    }

}
