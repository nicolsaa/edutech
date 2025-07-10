package CoffeTeamSF.EduTech.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import CoffeTeamSF.EduTech.dto.ContenidoAlmacenarDTO;
import CoffeTeamSF.EduTech.dto.ContenidoDTOputmapping;
import CoffeTeamSF.EduTech.model.Contenido;
import CoffeTeamSF.EduTech.service.ContenidoService;


//Made by Vicente Aravena

@RestController
@RequestMapping("/contenidos")
public class ContenidoController {
    @Autowired
    private ContenidoService contenidoService;

    // Método almacenar
    @PostMapping
    public ResponseEntity<String> almacenar(@RequestBody ContenidoAlmacenarDTO contenidoDTO) {
        String resultado = contenidoService.almacenar(contenidoDTO);
        return ResponseEntity.ok(resultado);
    }

    // Método listar
    @GetMapping
    public List<Contenido> listar() {
        return contenidoService.listar();
    }

    // Método modificar
    @PutMapping("/{id}")
    public String modificarContenido(@PathVariable Long id, @RequestBody ContenidoDTOputmapping contenidoDTOputmapping) { 
        return contenidoService.modificarContenido(id, contenidoDTOputmapping);
    }

    // Método eliminar
    @DeleteMapping("/{id}")
    public String eliminarContenido(@PathVariable Long id) {
        return contenidoService.eliminarContenido(id);
    }



    
}



