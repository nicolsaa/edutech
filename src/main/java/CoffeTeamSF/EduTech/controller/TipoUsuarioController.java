package CoffeTeamSF.EduTech.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import CoffeTeamSF.EduTech.dto.TipoUsuarioDTO;
import CoffeTeamSF.EduTech.model.TipoUsuario;
import CoffeTeamSF.EduTech.service.TipoUsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/tipoUsuarios")
public class TipoUsuarioController {


    @Autowired
    private TipoUsuarioService tipoUsuarioService;

    @PostMapping
    public String almacenar(@RequestBody TipoUsuarioDTO tipoUsuarioDTO){
        return tipoUsuarioService.almacenar(tipoUsuarioDTO);
    }

    @GetMapping
    public List <TipoUsuario> listar(){
        return tipoUsuarioService.listar();
    } 

    /*METODO QUE MODIFICA UN USUARIO POR SU ID
     * BY: NICOL SAAVEDRA
     */

    @PutMapping("/modificar/{id}")
    public ResponseEntity<String> modificarTipoUsuario(@PathVariable Long id,
        @RequestBody TipoUsuarioDTO tipoUsuarioDTO){
        
        tipoUsuarioDTO.setIdTipoUsuario(id);;
        String resultado = tipoUsuarioService.modificarTipoUsuario(tipoUsuarioDTO);
        return ResponseEntity.ok(resultado);
    }

}
