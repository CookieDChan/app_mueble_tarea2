package com.example.demo.service;

import com.example.demo.model.Mueble;
import com.example.demo.repository.MuebleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test del servicio de gestion de catalogo (CRUD)
 * Verifica que las operaciones CRUD funcionen correctamente
 */
@ExtendWith(MockitoExtension.class)
public class MuebleServiceTest {

    @Mock
    private MuebleRepository muebleRepository;

    @InjectMocks
    private MuebleService muebleService;

    @Test
    public void testCrearMueble() {
        // Arrange
        Mueble mueble = new Mueble("Silla", "Silla", 100.0, 10, Mueble.Tamano.MEDIANO, "Madera");
        when(muebleRepository.save(any(Mueble.class))).thenReturn(mueble);

        // Act
        Mueble resultado = muebleService.crearMueble(mueble);

        // Assert
        assertNotNull(resultado);
        assertEquals("Silla", resultado.getNombre());
        verify(muebleRepository, times(1)).save(mueble);
    }

    @Test
    public void testListarTodos() {
        // Arrange
        Mueble m1 = new Mueble("Silla", "Silla", 100.0, 10, Mueble.Tamano.MEDIANO, "Madera");
        Mueble m2 = new Mueble("Mesa", "Mesa", 200.0, 5, Mueble.Tamano.GRANDE, "Roble");
        when(muebleRepository.findAll()).thenReturn(Arrays.asList(m1, m2));

        // Act
        List<Mueble> resultado = muebleService.listarTodos();

        // Assert
        assertEquals(2, resultado.size());
        verify(muebleRepository, times(1)).findAll();
    }

    @Test
    public void testObtenerPorId() {
        // Arrange
        Long id = 1L;
        Mueble mueble = new Mueble("Silla", "Silla", 100.0, 10, Mueble.Tamano.MEDIANO, "Madera");
        mueble.setId(id);
        when(muebleRepository.findById(id)).thenReturn(Optional.of(mueble));

        // Act
        Mueble resultado = muebleService.obtenerPorId(id);

        // Assert
        assertNotNull(resultado);
        assertEquals("Silla", resultado.getNombre());
        verify(muebleRepository, times(1)).findById(id);
    }

    @Test
    public void testActualizarMueble() {
        // Arrange
        Long id = 1L;
        Mueble muebleExistente = new Mueble("Silla", "Silla", 100.0, 10, Mueble.Tamano.MEDIANO, "Madera");
        muebleExistente.setId(id);
        Mueble muebleActualizado = new Mueble("Silla Premium", "Silla", 150.0, 15, Mueble.Tamano.GRANDE, "Roble");

        when(muebleRepository.findById(id)).thenReturn(Optional.of(muebleExistente));
        when(muebleRepository.save(any(Mueble.class))).thenReturn(muebleExistente);

        // Act
        Mueble resultado = muebleService.actualizarMueble(id, muebleActualizado);

        // Assert
        assertNotNull(resultado);
        assertEquals("Silla Premium", resultado.getNombre());
        assertEquals(150.0, resultado.getPrecioBase());
        verify(muebleRepository, times(1)).save(any(Mueble.class));
    }

    @Test
    public void testDesactivarMueble() {
        // Arrange
        Long id = 1L;
        Mueble mueble = new Mueble("Silla", "Silla", 100.0, 10, Mueble.Tamano.MEDIANO, "Madera");
        mueble.setId(id);
        when(muebleRepository.findById(id)).thenReturn(Optional.of(mueble));

        // Act
        boolean resultado = muebleService.desactivarMueble(id);

        // Assert
        assertTrue(resultado);
        assertEquals(Mueble.Estado.INACTIVO, mueble.getEstado());
        verify(muebleRepository, times(1)).save(mueble);
    }

    @Test
    public void testHayStockSuficiente() {
        // Arrange
        Long id = 1L;
        Mueble mueble = new Mueble("Silla", "Silla", 100.0, 10, Mueble.Tamano.MEDIANO, "Madera");
        mueble.setId(id);
        when(muebleRepository.findById(id)).thenReturn(Optional.of(mueble));

        // Act
        boolean resultado = muebleService.hayStock(id, 5);

        // Assert
        assertTrue(resultado);
    }

    @Test
    public void testNoHayStockSuficiente() {
        // Arrange
        Long id = 1L;
        Mueble mueble = new Mueble("Silla", "Silla", 100.0, 10, Mueble.Tamano.MEDIANO, "Madera");
        mueble.setId(id);
        when(muebleRepository.findById(id)).thenReturn(Optional.of(mueble));

        // Act
        boolean resultado = muebleService.hayStock(id, 15);

        // Assert
        assertFalse(resultado);
    }
}
