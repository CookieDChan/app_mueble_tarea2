package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.CotizacionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test del servicio de stock/venta
 * Verifica que las ventas se confirmen correctamente y que se valide el stock
 */
@ExtendWith(MockitoExtension.class)
public class CotizacionServiceTest {

    @Mock
    private CotizacionRepository cotizacionRepository;

    @Mock
    private MuebleService muebleService;

    @Mock
    private VarianteService varianteService;

    @InjectMocks
    private CotizacionService cotizacionService;

    @Test
    public void testConfirmarVentaConStockSuficiente() {
        // Arrange
        Long cotizacionId = 1L;
        Mueble mueble = new Mueble("Silla", "Silla", 100.0, 10, Mueble.Tamano.MEDIANO, "Madera");
        mueble.setId(1L);

        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setId(cotizacionId);
        DetalleCotizacion detalle = new DetalleCotizacion(mueble, null, 5);
        cotizacion.agregarDetalle(detalle);
        cotizacion.calcularTotal();

        when(cotizacionRepository.findById(cotizacionId)).thenReturn(Optional.of(cotizacion));
        when(muebleService.hayStock(1L, 5)).thenReturn(true);
        when(cotizacionRepository.save(any(Cotizacion.class))).thenReturn(cotizacion);

        // Act
        Cotizacion resultado = cotizacionService.confirmarVenta(cotizacionId);

        // Assert
        assertEquals(Cotizacion.EstadoCotizacion.VENTA, resultado.getEstado());
        verify(muebleService, times(1)).actualizarStock(1L, 5);
        verify(cotizacionRepository, times(1)).save(any(Cotizacion.class));
    }

    @Test
    public void testConfirmarVentaConStockInsuficiente() {
        // Arrange
        Long cotizacionId = 1L;
        Mueble mueble = new Mueble("Silla", "Silla", 100.0, 2, Mueble.Tamano.MEDIANO, "Madera");
        mueble.setId(1L);

        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setId(cotizacionId);
        DetalleCotizacion detalle = new DetalleCotizacion(mueble, null, 5);
        cotizacion.agregarDetalle(detalle);
        cotizacion.calcularTotal();

        when(cotizacionRepository.findById(cotizacionId)).thenReturn(Optional.of(cotizacion));
        when(muebleService.hayStock(1L, 5)).thenReturn(false);

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cotizacionService.confirmarVenta(cotizacionId);
        });

        assertTrue(exception.getMessage().contains("Stock insuficiente"));
        verify(muebleService, never()).actualizarStock(any(), any());
        verify(cotizacionRepository, never()).save(any(Cotizacion.class));
    }

    @Test
    public void testConfirmarVentaYaConfirmada() {
        // Arrange
        Long cotizacionId = 1L;
        Cotizacion cotizacion = new Cotizacion();
        cotizacion.setId(cotizacionId);
        cotizacion.setEstado(Cotizacion.EstadoCotizacion.VENTA);

        when(cotizacionRepository.findById(cotizacionId)).thenReturn(Optional.of(cotizacion));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            cotizacionService.confirmarVenta(cotizacionId);
        });

        assertTrue(exception.getMessage().contains("ya fue confirmada"));
    }
}
