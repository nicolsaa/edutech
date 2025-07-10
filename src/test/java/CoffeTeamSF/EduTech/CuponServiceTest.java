package CoffeTeamSF.EduTech;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import CoffeTeamSF.EduTech.model.Cupon;
import CoffeTeamSF.EduTech.repository.CuponRepository;
import CoffeTeamSF.EduTech.service.CuponService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//
//**************DESARROLLADO POR RICARDO CUEVAS**************************+ */
//

@ExtendWith(MockitoExtension.class)
public class CuponServiceTest {

    @Mock
    private CuponRepository cuponRepository;

    @InjectMocks
    private CuponService cuponService;

    @Test
    void testAlmacenarCupon() {
        Cupon cupon = new Cupon();
        cupon.setCodigo("NUEVO20");
        cupon.setPorcentaje_descuento(20);
        cupon.setFecha_expiracion("2025-11-30");

        when(cuponRepository.existsById("NUEVO20")).thenReturn(false);

        String resultado = cuponService.almacenar(cupon);

        assertEquals("Cupon agregado correctamente", resultado);
    }

    @Test
    void testListarCupones() {
        Cupon c1 = new Cupon();
        c1.setCodigo("C1");
        c1.setPorcentaje_descuento(15);
        c1.setFecha_expiracion("2025-09-01");

        Cupon c2 = new Cupon();
        c2.setCodigo("C2");
        c2.setPorcentaje_descuento(25);
        c2.setFecha_expiracion("2025-10-01");

        List<Cupon> listaSimulada = new ArrayList<>();
        listaSimulada.add(c1);
        listaSimulada.add(c2);

        when(cuponRepository.findAll()).thenReturn(listaSimulada);

        List<Cupon> resultado = cuponService.listar();

        assertEquals(2, resultado.size());
        assertEquals("C1", resultado.get(0).getCodigo());
        assertEquals("C2", resultado.get(1).getCodigo());
    }

    @Test
    void testEliminarCupon() {
        Cupon cupon = new Cupon();
        cupon.setCodigo("BORRAR10");
        cupon.setPorcentaje_descuento(10);
        cupon.setFecha_expiracion("2025-07-15");

        when(cuponRepository.findById("BORRAR10")).thenReturn(Optional.of(cupon));

        String resultado = cuponService.eliminarCupon("BORRAR10");

        verify(cuponRepository, times(1)).delete(cupon);
        assertEquals("Cupon eliminado correctamente.", resultado);
    }
}
