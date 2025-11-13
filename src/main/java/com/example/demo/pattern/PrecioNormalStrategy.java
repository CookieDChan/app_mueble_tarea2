package com.example.demo.pattern;

import com.example.demo.model.Mueble;
import com.example.demo.model.Variante;

public class PrecioNormalStrategy implements PrecioStrategy {

    @Override
    public Double calcularPrecio(Mueble mueble, Variante variante) {
        return mueble.getPrecioBase();
    }
}
