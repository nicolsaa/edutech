package CoffeTeamSF.EduTech.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import CoffeTeamSF.EduTech.dto.InscripcionDTO;
import CoffeTeamSF.EduTech.dto.InscripcionFormapagoDTO;
import CoffeTeamSF.EduTech.model.Cupon;
import CoffeTeamSF.EduTech.model.Curso;
import CoffeTeamSF.EduTech.model.FormaPago;
import CoffeTeamSF.EduTech.model.Inscripcion;
import CoffeTeamSF.EduTech.model.Usuario;
import CoffeTeamSF.EduTech.repository.CursoRepository;
import CoffeTeamSF.EduTech.repository.FormaPagoRepository;
import CoffeTeamSF.EduTech.repository.InscripcionRepository;
import CoffeTeamSF.EduTech.repository.UsuarioRepository;


/*INSCRIPCION 
  * BY NICOL SAAVEDRA
  */
@Service
public class InscripcionService {
    @Autowired 
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private FormaPagoRepository formaPagoRepository;

    /*METODO QUE INSCRIBE UN TIPO DE USUSARIO A UN CURSO ESPECIFICO
     * BY NICOL SAAVEDRA
     */
    public String inscribirTipoUsuarioACurso(InscripcionDTO inscripcionDTO ){
    Optional<Usuario> usuarioOpt = usuarioRepository.findById(inscripcionDTO.getIdUsuario());

        if (!usuarioOpt.isPresent()) {
            return "no existe usuario";
        }

        Usuario usuario = usuarioOpt.get();

        if (usuario.getTipoUsuario() == null) {
            return "usuario no tiene un tipo asociado";
        }

    Optional<Curso> cursoOptional = cursoRepository.findById(inscripcionDTO.getSigla());

        if (!cursoOptional.isPresent()) {
            return "curso no existe";
        }

    Optional<List<Inscripcion>> inscripcionesOpt = inscripcionRepository.findByUsuario(usuario);

        if (inscripcionesOpt.isPresent()) {
            for (Inscripcion inscripcion :  inscripcionesOpt.get()) {
                if (inscripcionDTO.getSigla() == inscripcion.getCurso().getSigla()) {
                    return "usuario ya está registrado al curso";
                }
            }
        }

        Inscripcion inscripcion = new Inscripcion();

        inscripcion.setCurso(cursoOptional.get());
        inscripcion.setUsuario(usuario);
        inscripcion.setFechaInscripcion(LocalDate.now());

        inscripcionRepository.save(inscripcion);

        return "usuario inscrito con exito";
    }

    /*Metodo para asignar forma de pago by RICARDO CUEVAS */
    public String asignarFormaPagoAInscripcion(Long idInscripcion, String idFormaPago) {
    Optional<Inscripcion> inscripcionOpt = inscripcionRepository.findById(idInscripcion);
    Optional<FormaPago> formaPagoOpt = formaPagoRepository.findById(idFormaPago);

        if (!inscripcionOpt.isPresent()) {
            return "Inscripción no encontrada";
        }

        if (!formaPagoOpt.isPresent()) {
            return "Forma de pago no encontrada";
        }

        Inscripcion inscripcion = inscripcionOpt.get();
        FormaPago formaPago = formaPagoOpt.get();

        inscripcion.setFormaPago(formaPago);
        inscripcionRepository.save(inscripcion);

        return "Forma de pago asignada correctamente a la inscripción";
    }

    /*Metodo para listar inscripciones con su forma de pago  by RICARDO CUEVAS */
    public List<InscripcionFormapagoDTO> obtenerInscripcionesConFormaPagoYDescuento() {
        List<Inscripcion> inscripciones = inscripcionRepository.findAll();

        return inscripciones.stream().map(inscripcion -> {
            FormaPago formaPago = inscripcion.getFormaPago();

            String descripcion = (formaPago != null) ? formaPago.getDescripcion() : "Sin forma de pago";

            // Obtener descuento si hay cupon asociado
            Integer porcentajeDescuento = null;
            if (formaPago != null && formaPago.getCupons() != null && !formaPago.getCupons().isEmpty()) {
                Cupon primerCupon = formaPago.getCupons().get(0);
                porcentajeDescuento = primerCupon.getPorcentaje_descuento();
            }

            return new InscripcionFormapagoDTO(
                inscripcion.getId(),
                inscripcion.getUsuario().getNombre(),
                inscripcion.getCurso().getNombre(),
                inscripcion.getFechaInscripcion(),
                descripcion,
                porcentajeDescuento
            );
        }).toList();
    }

}
