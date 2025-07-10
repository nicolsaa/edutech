package CoffeTeamSF.EduTech.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CoffeTeamSF.EduTech.dto.ResenaAlmacenarDTO;
import CoffeTeamSF.EduTech.dto.ResenaListarDTO3;
import CoffeTeamSF.EduTech.service.ResenaService;


//MADE BY VICENTE ARAVENA

@RestController
@RequestMapping("/resenas")
public class ResenaController {
    @Autowired
    private ResenaService resenaService;

    
    //Método almacenar reseñas
    @PostMapping
    public ResponseEntity<String> almacenar(@RequestBody ResenaAlmacenarDTO resenaDTO) {
        return resenaService.almacenarResena(resenaDTO);
    }



    //Método listar reseñas
    @GetMapping
    public List<ResenaListarDTO3> listarResenasConDatosDeCurso() {
        return resenaService.listarResenas();
    }





    

}
