package CoffeTeamSF.EduTech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import CoffeTeamSF.EduTech.dto.InscripcionDTO;
import CoffeTeamSF.EduTech.service.InscripcionService;


/*CONTROLLER
  * BY NICOL SAAVEDRA
  */
@RestController
@RequestMapping("/inscribir")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;


    @PostMapping
    public ResponseEntity<String> crearInscripcion(@RequestBody InscripcionDTO inscripcionDTO) {
        String resultado = inscripcionService.inscribirTipoUsuarioACurso(inscripcionDTO);
        
        if(resultado.equals("usuario inscrito con exito")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.badRequest().body(resultado);
        }
    }

}
