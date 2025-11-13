package com.example.demo.pattern;

import com.example.demo.model.Mueble;
import com.example.demo.model.Variante;

public class PrecioConDescuentoStrategy implements PrecioStrategy {

    private final Double porcentajeDescuento;

    public PrecioConDescuentoStrategy(Double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    @Override
    public Double calcularPrecio(Mueble mueble, Variante variante) {
        Double precioBase = mueble.getPrecioBase();
        if (variante != null) {
            precioBase += variante.getPrecioAdicional();
        }
        return precioBase * (1 - porcentajeDescuento / 100);
    }
}
