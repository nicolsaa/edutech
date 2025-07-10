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

import CoffeTeamSF.EduTech.dto.IncidenciaAlmacenarDTO;
import CoffeTeamSF.EduTech.dto.IncidenciaListarDTO;
import CoffeTeamSF.EduTech.dto.IncidenciaResolverDTO;
import CoffeTeamSF.EduTech.service.IncidenciaService;


//MADE BY VICENTE ARAVENA
@RestController
@RequestMapping("/incidencias")
public class IncidenciaController {
    @Autowired
    private IncidenciaService incidenciaService;


    //Método almacenar incidencias (Cambio a dto el 29/6)
    @PostMapping
    public ResponseEntity<String> almacenar(@RequestBody IncidenciaAlmacenarDTO incidenciaDTO) {
        return incidenciaService.almacenarIncidencia(incidenciaDTO);
    }




    //Método listar incidencias
    @GetMapping
    public ResponseEntity<List<IncidenciaListarDTO>> listarIncidencias() {
        List<IncidenciaListarDTO> incidencias = incidenciaService.listar();
        return ResponseEntity.ok(incidencias);
    }





    //Método Resolver Incidencias (29/6)
    @PutMapping("/resolver/{idIncidencia}")
    public ResponseEntity<String> resolverIncidencia(
            @PathVariable Long idIncidencia,
            @RequestBody IncidenciaResolverDTO dto) {
        return incidenciaService.resolverIncidencia(idIncidencia, dto.getIdUsuario());
    }







}
