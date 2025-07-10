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

import CoffeTeamSF.EduTech.dto.CursoDTOputmapping;
import CoffeTeamSF.EduTech.dto.CursoListarDTO;
import CoffeTeamSF.EduTech.dto.AsignarProfesorDTO;
import CoffeTeamSF.EduTech.dto.CursoAlmacenarDTO;
import CoffeTeamSF.EduTech.service.CursoService;



//Made by Vicente Aravena

@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoService cursoService;


    //Método almacenar (Made by Vicente Aravena)
    @PostMapping 
    public ResponseEntity<String> almacenar(@RequestBody CursoAlmacenarDTO dto) {
        return cursoService.almacenar(dto);
    }



    //Método listar:
    @GetMapping
    public List<CursoListarDTO> listarCursos() {
        return cursoService.listar();
    }


    //Método modificar (Made by Vicente Aravena esta parte de Controller, lo de Service es de Nicol Saavedra)
    @PutMapping("/{sigla}")
    public String modificarCurso(@PathVariable String sigla, @RequestBody CursoDTOputmapping cursoDTOputmapping) {
        return cursoService.modificarCurso(sigla, cursoDTOputmapping);
    }

    //Método eliminar (referencia al de service): (Made by Vicente Aravena)
    @DeleteMapping("/{sigla}")
    public String eliminarCurso(@PathVariable String sigla){
        return cursoService.eliminarCurso(sigla);
    }


    //Método asignar profesor a curso (Made by Vicente Aravena)
    @PostMapping("/asignarProfesor")
    public ResponseEntity<String> asignarProfesorACurso(@RequestBody AsignarProfesorDTO dto) {
        return cursoService.asignarProfesorACurso(dto);
    }




    
}
