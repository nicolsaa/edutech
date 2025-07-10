package CoffeTeamSF.EduTech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import CoffeTeamSF.EduTech.dto.InscripcionDTO;
import CoffeTeamSF.EduTech.dto.InscripcionFormapagoDTO;
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
    public ResponseEntity<String> inscribirUsuarioAcurso(@RequestBody InscripcionDTO inscripcionDTO) {
        return ResponseEntity.ok()
            .body(this.inscripcionService
            .inscribirTipoUsuarioACurso(inscripcionDTO));
    }

    /*By RICARDO CUEVAS */
    @PutMapping("/{idInscripcion}/formapago/{idFormaPago}")
    public ResponseEntity<String> asignarFormaPagoAInscripcion(@PathVariable Long idInscripcion,@PathVariable String idFormaPago) {
        String respuesta = inscripcionService.asignarFormaPagoAInscripcion(idInscripcion, idFormaPago);
        return ResponseEntity.ok(respuesta);
    }

    
    @GetMapping("/listarConFormaPago")
    public ResponseEntity<List<InscripcionFormapagoDTO>> listarConFormaPagoYDescuento() {
        List<InscripcionFormapagoDTO> lista = inscripcionService.obtenerInscripcionesConFormaPagoYDescuento();
        return ResponseEntity.ok(lista);
    }


    

}
