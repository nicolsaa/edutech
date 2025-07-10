package CoffeTeamSF.EduTech;


import CoffeTeamSF.EduTech.model.FormaPago;
import CoffeTeamSF.EduTech.repository.CuponRepository;
import CoffeTeamSF.EduTech.repository.FormaPagoRepository;
import CoffeTeamSF.EduTech.service.FormaPagoService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


//
//**************DESARROLLADO POR RICARDO CUEVAS**************************+ */
//

@ExtendWith(MockitoExtension.class)
public class FormaPagoServiceTest {

    @Mock
    private CuponRepository cuponRepository;

    @Mock
    private FormaPagoRepository formaPagoRepository;

    @InjectMocks
    private FormaPagoService formaPagoService;

    @Test
    void testAlmacenarFormaPagoNueva() {
        FormaPago forma = new FormaPago();
        forma.setId("1");
        forma.setDescripcion("Crédito");

        when(formaPagoRepository.findByDescripcion("Crédito")).thenReturn(null);

        String resultado = formaPagoService.almacenar(forma);

        assertEquals("Forma de pago ingresada", resultado);
    }

    @Test
    void testAlmacenarFormaPagoYaExiste() {
        FormaPago existente = new FormaPago();
        existente.setId("2");
        existente.setDescripcion("Débito");

        when(formaPagoRepository.findByDescripcion("Débito")).thenReturn(existente);

        FormaPago forma = new FormaPago();
        forma.setId("2");
        forma.setDescripcion("Débito");

        String resultado = formaPagoService.almacenar(forma);

        assertEquals("Forma de pago Débito ya existe", resultado);
    }

    @Test
    void testListarFormasPago() {
        FormaPago f1 = new FormaPago();
        f1.setId("1");
        f1.setDescripcion("Crédito");

        FormaPago f2 = new FormaPago();
        f2.setId("2");
        f2.setDescripcion("Débito");

        List<FormaPago> listaSimulada = Arrays.asList(f1, f2);

        when(formaPagoRepository.findAll()).thenReturn(listaSimulada);

        List<FormaPago> resultado = formaPagoService.listar();

        assertEquals(2, resultado.size());
        assertEquals("Crédito", resultado.get(0).getDescripcion());
    }

    @Test
    void testBuscarFormasPagoPorDescripcion() {
        FormaPago f = new FormaPago();
        f.setId("3");
        f.setDescripcion("Transferencia Bancaria");

        List<FormaPago> resultadosSimulados = List.of(f);

        when(formaPagoRepository.findByDescripcionContaining("Transferencia")).thenReturn(resultadosSimulados);

        List<FormaPago> resultado = formaPagoService.buscar("Transferencia");

        assertEquals(1, resultado.size());
        assertEquals("Transferencia Bancaria", resultado.get(0).getDescripcion());
    }

    @Test
    void testAgregarCuponFormaPagoNoExiste() {
        when(formaPagoRepository.existsById("123")).thenReturn(false);

        String resultado = formaPagoService.agregarCupon("123", "CUPON10");

        assertEquals("La forma de pago no existe", resultado);
    }

    @Test
    void testAgregarCuponNoExiste() {
        when(formaPagoRepository.existsById("123")).thenReturn(true);
        when(cuponRepository.existsById("CUPON10")).thenReturn(false);

        String resultado = formaPagoService.agregarCupon("123", "CUPON10");

        assertEquals("Cupon no encontrado", resultado);
    }

    @Test
    void testEliminarFormaPagoExistente() {
        FormaPago forma = new FormaPago();
        forma.setId("999");
        forma.setDescripcion("Bitcoin");

        when(formaPagoRepository.findById("999")).thenReturn(Optional.of(forma));

        String resultado = formaPagoService.eliminarFormaPago("999");

        assertEquals("La forma de pago ya no existe", resultado);
        verify(formaPagoRepository).delete(forma);
    }

}

