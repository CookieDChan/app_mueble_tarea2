package com.example.demo.pattern;

import com.example.demo.model.Mueble;
import com.example.demo.model.Variante;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test del patron Strategy para calculo de precios
 */
public class PrecioStrategyTest {

    @Test
    public void testPrecioNormalStrategy() {
        // Arrange
        Mueble mueble = new Mueble("Silla", "Silla", 100.0, 10, Mueble.Tamano.MEDIANO, "Madera");
        CalculadoraPrecio calculadora = new CalculadoraPrecio(new PrecioNormalStrategy());

        // Act
        Double precio = calculadora.calcular(mueble, null);

        // Assert
        assertEquals(100.0, precio);
    }

    @Test
    public void testPrecioConVarianteStrategy() {
        // Arrange
        Mueble mueble = new Mueble("Silla", "Silla", 100.0, 10, Mueble.Tamano.MEDIANO, "Madera");
        Variante variante = new Variante("Barniz Premium", "Acabado premium", 50.0);
        CalculadoraPrecio calculadora = new CalculadoraPrecio(new PrecioConVarianteStrategy());

        // Act
        Double precio = calculadora.calcular(mueble, variante);

        // Assert
        assertEquals(150.0, precio);
    }

    @Test
    public void testPrecioConDescuentoStrategy() {
        // Arrange
        Mueble mueble = new Mueble("Silla", "Silla", 100.0, 10, Mueble.Tamano.MEDIANO, "Madera");
        Variante variante = new Variante("Barniz Premium", "Acabado premium", 50.0);
        CalculadoraPrecio calculadora = new CalculadoraPrecio(new PrecioConDescuentoStrategy(10.0));

        // Act
        Double precio = calculadora.calcular(mueble, variante);

        // Assert
        assertEquals(135.0, precio);
    }

    @Test
    public void testCambiarEstrategia() {
        // Arrange
        Mueble mueble = new Mueble("Silla", "Silla", 100.0, 10, Mueble.Tamano.MEDIANO, "Madera");
        CalculadoraPrecio calculadora = new CalculadoraPrecio(new PrecioNormalStrategy());

        // Act
        Double precioNormal = calculadora.calcular(mueble, null);
        calculadora.setStrategy(new PrecioConDescuentoStrategy(20.0));
        Double precioConDescuento = calculadora.calcular(mueble, null);

        // Assert
        assertEquals(100.0, precioNormal);
        assertEquals(80.0, precioConDescuento);
    }
}
