package com.example.demo.pattern;

import com.example.demo.model.Mueble;
import com.example.demo.model.Variante;

public class PrecioConVarianteStrategy implements PrecioStrategy {

    @Override
    public Double calcularPrecio(Mueble mueble, Variante variante) {
        Double precioBase = mueble.getPrecioBase();
        if (variante != null) {
            return precioBase + variante.getPrecioAdicional();
        }
        return precioBase;
    }
}
