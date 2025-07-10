package CoffeTeamSF.EduTech.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import CoffeTeamSF.EduTech.dto.TipoUsuarioDTO;
import CoffeTeamSF.EduTech.model.TipoUsuario;
import CoffeTeamSF.EduTech.repository.TipoUsuarioRepository;


@Service
public class TipoUsuarioService {
    @Autowired
    private TipoUsuarioRepository tipoUsuarioRepository;


    /*METODO QUE ALMACENA UN TIPO DE USUSARIO
     * BY NICOL SAAVEDRA
     */
    public String almacenar(TipoUsuarioDTO tipoUsuarioDTO){
        if(tipoUsuarioRepository.findByNombre(tipoUsuarioDTO.getNombre()) == null) {

            TipoUsuario tipoUsuario = new TipoUsuario();

            tipoUsuario.setNombre(tipoUsuarioDTO.getNombre());

            tipoUsuarioRepository.save(tipoUsuario);

            return "Tipo de usuario " + tipoUsuario.getNombre() + " almacenado correctamente";
        }
        return "El tipo de usuario con nombre: " + tipoUsuarioDTO.getNombre() + " ya existe";
    }


    /*METODO QUE LISTA LOS TIPOS DE USUSARIO
     * BY: NICOL SAAVEDRA
     */
    public List <TipoUsuario> listar(){
        return tipoUsuarioRepository.findAll();
    }


    /*METODO QUE MODIFICA UN TIPO DE USUSARIO POR SU ID
     * BY: NICOL SAAVEDRA
     */
    public String modificarTipoUsuario (TipoUsuarioDTO tipoUsuarioDTO){
    Optional<TipoUsuario> tipoUsuarioOpt = tipoUsuarioRepository.findById(tipoUsuarioDTO.getIdTipoUsuario());

        if (tipoUsuarioOpt.isPresent()) {
            TipoUsuario tipoUsuario = tipoUsuarioOpt.get();

            if (tipoUsuarioDTO.getNombre() != null) {
                tipoUsuario.setNombre(tipoUsuarioDTO.getNombre());
            }
            tipoUsuarioRepository.save(tipoUsuario);
            return "Tipo de usuario modificado correctamente";
        }else{
            return "Tipo de usuario no encontrado";
        }
    }

}
