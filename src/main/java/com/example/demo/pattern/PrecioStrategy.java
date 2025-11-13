package com.example.demo.pattern;

import com.example.demo.model.Mueble;
import com.example.demo.model.Variante;

public interface PrecioStrategy {
    Double calcularPrecio(Mueble mueble, Variante variante);
}
