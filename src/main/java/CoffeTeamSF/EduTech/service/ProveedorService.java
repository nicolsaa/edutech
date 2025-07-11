package CoffeTeamSF.EduTech.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import CoffeTeamSF.EduTech.dto.ProveedorUsuarioDTO;
import CoffeTeamSF.EduTech.model.Proveedor;
import CoffeTeamSF.EduTech.model.Usuario;
import CoffeTeamSF.EduTech.repository.ProveedorRepository;
import CoffeTeamSF.EduTech.repository.UsuarioRepository;


//
//**************DESARROLLADO POR RICARDO CUEVAS**************************+ */
//

@Service
public class ProveedorService {
    @Autowired
    private ProveedorRepository proveedorRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;


    public String almacenar(Proveedor proveedor){
        if(proveedorRepository.existsById(proveedor.getId())){
            return "EL proveedor ya existe en las bases de datos";
        }else{
            proveedorRepository.save(proveedor);
            return "El proveedor ha sido agregado correctamente";
        } 
    }


    public List<ProveedorUsuarioDTO> listarProveedoresConUsuario() {
        List<Proveedor> proveedores = proveedorRepository.findAll();

        return proveedores.stream().map(proveedor -> {
            Long usuarioId = null;
            String usuarioNombre = null;

            if (proveedor.getUsuario() != null) {
                usuarioId = proveedor.getUsuario().getId();
                usuarioNombre = proveedor.getUsuario().getNombre();
            }

            return new ProveedorUsuarioDTO(
                proveedor.getId(),
                proveedor.getTipo_servicio(),
                proveedor.getNombre(),
                proveedor.getContacto(),
                usuarioId,
                usuarioNombre
            );
        }).collect(Collectors.toList());
    }


    public String asociarProveedorAUsuario(String idProveedor, Long idUsuario) {
        Optional<Proveedor> proveedorOpt = proveedorRepository.findById(idProveedor);
        if (proveedorOpt.isEmpty()) {
            return "Proveedor no encontrado";
        }

        Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario);
        if (usuarioOpt.isEmpty()) {
            return "Usuario no encontrado";
        }

        Usuario usuario = usuarioOpt.get();

        if (usuario.getTipoUsuario() == null || !"GerenteCursos".equals(usuario.getTipoUsuario().getNombre())) {
            return "Solo usuarios con tipo GerenteCursos pueden asociar proveedores";
        }

        Proveedor proveedor = proveedorOpt.get();
        proveedor.setUsuario(usuario);

        proveedorRepository.save(proveedor);

        return "Proveedor asociado correctamente al usuario";
    }
    


}
