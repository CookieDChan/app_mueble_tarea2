package com.example.demo.pattern;

import com.example.demo.model.Mueble;
import com.example.demo.model.Variante;


public class CalculadoraPrecio {

    private PrecioStrategy strategy;

    public CalculadoraPrecio(PrecioStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PrecioStrategy strategy) {
        this.strategy = strategy;
    }

    public Double calcular(Mueble mueble, Variante variante) {
        return strategy.calcularPrecio(mueble, variante);
    }
}
