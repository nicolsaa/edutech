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
import CoffeTeamSF.EduTech.dto.TipoUsuarioDTO;
import CoffeTeamSF.EduTech.dto.UsuarioDTO;
import CoffeTeamSF.EduTech.model.Usuario;
import CoffeTeamSF.EduTech.service.UsuarioService;



@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;
    
    /*METODO QUE ALMACENA UN USUSARIO
     * BY: NICOL SAAVEDRA
     */
    @PostMapping
    public String almacenar(@RequestBody UsuarioDTO usuarioDTO){
        return usuarioService.registrarUsuario(usuarioDTO);
    }

    /*METODO QUE LLAMA LA LISTA DE USUSARIO 
     * BY: NICOL SAAVEDRA
    */
    @GetMapping
    public List<UsuarioDTO> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }
    /*metodo que lista por tipo de usuario
     * by Nicol Saavedra
     */
    @GetMapping("/por-tipo/{tipo}")
    public List<Usuario> listarPorTipo(@PathVariable String tipo){
        return usuarioService.listarUsuariosPorTipoNombre(tipo);
    }


    /*METODO QUE ASIGNA UN USUARIO A UN TIPO DE USUSARIO
     * BY: NICOL SAAVEDRA
     */

    @PostMapping("/asignarTipoUsuario")
    public ResponseEntity<String> asignarTipoUsuario(@RequestBody TipoUsuarioDTO tipoUsuarioDTO){
        return ResponseEntity.ok(usuarioService.asignarTipoUsuario(tipoUsuarioDTO));
        
    }

    /*METODO QUE ELIMINA UN USUARIO
     * BY: NICOL SAAVEDRA
     */
    @DeleteMapping("/{correo}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable String correo){
        return usuarioService.eliminarUsuarioPorCorreo(correo);
    }
    /*METODO QUE MODIFICA UN USUARIO POR SU ID
     * BY: NICOL SAAVEDRA
     */
    @PutMapping("/modificar/{id}")
    public ResponseEntity<String> modificarUsuario(@PathVariable Long id,
        @RequestBody UsuarioDTO usuarioDTO){
        
        usuarioDTO.setId(id);
        String resultado = usuarioService.modificarUsusario(usuarioDTO);
        return ResponseEntity.ok(resultado);
    }


    
}
