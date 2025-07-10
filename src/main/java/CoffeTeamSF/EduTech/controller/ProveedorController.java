package CoffeTeamSF.EduTech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import CoffeTeamSF.EduTech.dto.ProveedorUsuarioDTO;
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

    @GetMapping("/con-usuario")
    public List<ProveedorUsuarioDTO> listarProveedoresConUsuario() {
        return proveedorService.listarProveedoresConUsuario();
    }

    @PostMapping("/asociarUsuario/{idProveedor}/{idUsuario}")
    public ResponseEntity<String> asociarProveedorAUsuario(
        @PathVariable String idProveedor,
        @PathVariable Long idUsuario) {

        String resultado = proveedorService.asociarProveedorAUsuario(idProveedor, idUsuario);

        if (resultado.contains("correctamente")) {
            return ResponseEntity.ok(resultado);
        } else {
            return ResponseEntity.badRequest().body(resultado);
        }
    }
}
