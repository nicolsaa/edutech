package CoffeTeamSF.EduTech.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import CoffeTeamSF.EduTech.dto.IncidenciaAlmacenarDTO;
import CoffeTeamSF.EduTech.dto.IncidenciaListarDTO;
import CoffeTeamSF.EduTech.model.Incidencia;
import CoffeTeamSF.EduTech.model.Usuario;
import CoffeTeamSF.EduTech.repository.IncidenciaRepository;
import CoffeTeamSF.EduTech.repository.UsuarioRepository;


//MADE BY VICENTE ARAVENA


@Service
public class IncidenciaService {
    @Autowired
    private IncidenciaRepository incidenciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository; 



    //Método almacenar incidencias (Made by Vicente Aravena) 29/6
    public ResponseEntity<String> almacenarIncidencia(IncidenciaAlmacenarDTO incidenciaAlmacenarDTO) {
        //1) Se verifica si es que existe o no usuario para poder crear la incidencia, gracias al idUsuario que ingresamos en postman
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(incidenciaAlmacenarDTO.getIdUsuario());
        if (!usuarioOpt.isPresent()) { //Si el objeto usuarioOpt no es rellenado con un Usuario que exista en la BD, entonces...
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado. No se puede registrar la incidencia.");
        }
        //Si es que sí existe un Usuario con el id que nosotros ingresamos en postman entonces...
        Incidencia incidencia = new Incidencia();

        //2) Se rellenan todos los campos de la incidencia, incluyendo el nuevo campo Usuario que está en incidencia. Quedan anclados.
        incidencia.setDescripcion(incidenciaAlmacenarDTO.getDescripcion());
        incidencia.setEstado("Pendiente");
        incidencia.setFecha_incidencia(java.time.LocalDate.now().toString());
        incidencia.setFecha_resolucion("----------");
        incidencia.setUsuario(usuarioOpt.get());

        incidenciaRepository.save(incidencia); //Se guarda la incidencia en la BD, anclada a un Usuario.

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Incidencia registrada exitosamente.");
    }




    //Método listar incidencias usando DTO (Made by Vicente Aravena)
    public List<IncidenciaListarDTO> listar() {
        List<Incidencia> incidencias = incidenciaRepository.findAll();
        List<IncidenciaListarDTO> incidenciasDTO = new ArrayList<>();
        for (Incidencia incidencia : incidencias) {
            Long idUsuario = null;
            String nombreUsuario = null;
            if (incidencia.getUsuario() != null) {
                idUsuario = incidencia.getUsuario().getId();
                nombreUsuario = incidencia.getUsuario().getNombre();
            }
            incidenciasDTO.add(new IncidenciaListarDTO(
                incidencia.getId(),
                idUsuario,
                nombreUsuario,
                incidencia.getDescripcion(),
                incidencia.getEstado(),
                incidencia.getFecha_incidencia() != null ? incidencia.getFecha_incidencia().toString() : null,
                incidencia.getFecha_resolucion() != null ? incidencia.getFecha_resolucion().toString() : null
            ));
        }
        return incidenciasDTO;
    }







    //Método resolver incidencias (Made by Vicente Aravena):
    public ResponseEntity<String> resolverIncidencia(Long idIncidencia, Long idUsuario) {
        //Validación 1: Se verifica primero que un Usuario de la BD sí exista, mediante el idUsuario que pongamos en el body
        //Uso de optional sólo a la hora de verificar la existencia de un objeto, ya que permite manejar mejor objetos que podrían ser null
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(idUsuario); 
        if (!usuarioOpt.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado."); //Si no encuentra un Usuario real, lanza error.
        } //Si recién verifica que el id que ponemos en Body sí calza con un usuario real, entonces...
        Usuario usuario = usuarioOpt.get();

        //Validación 2: Se verifica que el tipoUsuario del Usuario que recibimos por Body no sea null ni sea distinto a "Soporte"
        if (usuario.getTipoUsuario() == null ||
            !"Soporte".equalsIgnoreCase(usuario.getTipoUsuario().getNombre())) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN) //Si su tipoUsuario es null o !NO "Soporte", entonces lanza error.
                    .body("El usuario no tiene permisos para resolver incidencias."); 
        }

        //Validación 3: Se verifica que la incidencia exista, tal como arriba con Usuario.
        Optional<Incidencia> incidenciaOpt = incidenciaRepository.findById(idIncidencia);
        if (!incidenciaOpt.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Incidencia no encontrada.");
        } //Si recién verifica que el id que ponemos en Body sí calza con una incidencia real, entonces...
        Incidencia incidencia = incidenciaOpt.get();

        //Se rellenan los campos de la incidencia a modo "resuelta"
        incidencia.setEstado("Resuelta");
        incidencia.setFecha_resolucion(java.time.LocalDate.now().toString());
        incidenciaRepository.save(incidencia); //ya que el método en controller es PUT, este save literalmente actualiza el objeto real.

        return ResponseEntity
                .ok("Incidencia resuelta correctamente.");
    }





}








