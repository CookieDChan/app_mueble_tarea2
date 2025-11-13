package com.example.demo.service;

import com.example.demo.model.Variante;
import com.example.demo.repository.VarianteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test del servicio de precios (variantes)
 * Verifica que el calculo de precios con variantes funcione correctamente
 */
@ExtendWith(MockitoExtension.class)
public class VarianteServiceTest {

    @Mock
    private VarianteRepository varianteRepository;

    @InjectMocks
    private VarianteService varianteService;

    @Test
    public void testCalcularPrecioConVariante() {
        // Arrange
        Double precioBase = 100.0;
        Long varianteId = 1L;
        Variante variante = new Variante("Barniz Premium", "Acabado premium", 50.0);

        when(varianteRepository.findById(varianteId)).thenReturn(Optional.of(variante));

        // Act
        Double precioFinal = varianteService.calcularPrecioConVariante(precioBase, varianteId);

        // Assert
        assertEquals(150.0, precioFinal);
        verify(varianteRepository, times(1)).findById(varianteId);
    }

    @Test
    public void testCalcularPrecioSinVariante() {
        // Arrange
        Double precioBase = 100.0;

        // Act
        Double precioFinal = varianteService.calcularPrecioConVariante(precioBase, null);

        // Assert
        assertEquals(100.0, precioFinal);
        verify(varianteRepository, never()).findById(any());
    }

    @Test
    public void testCalcularPrecioConVarianteInexistente() {
        // Arrange
        Double precioBase = 100.0;
        Long varianteId = 999L;

        when(varianteRepository.findById(varianteId)).thenReturn(Optional.empty());

        // Act
        Double precioFinal = varianteService.calcularPrecioConVariante(precioBase, varianteId);

        // Assert
        assertEquals(100.0, precioFinal);
        verify(varianteRepository, times(1)).findById(varianteId);
    }
}
