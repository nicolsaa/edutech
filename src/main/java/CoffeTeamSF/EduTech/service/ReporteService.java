package CoffeTeamSF.EduTech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import CoffeTeamSF.EduTech.model.Reporte;
import CoffeTeamSF.EduTech.repository.ReporteRepository;

//
//**************DESARROLLADO POR RICARDO CUEVAS**************************+ */
//

@Service
public class ReporteService {
    @Autowired
    private ReporteRepository reporteRepository;


    public String almacenar(Reporte reporte){
        if(reporteRepository.existsById(reporte.getId())){
            return "El id del reporte ya existe";
        }else{
            reporteRepository.save(reporte);
            return "El reporte ha sido enviado, lo estamos evaluando";
        } 
    }


    public List<Reporte> listar(){
        return reporteRepository.findAll();
    }
}
