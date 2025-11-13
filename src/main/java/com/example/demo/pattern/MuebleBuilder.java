package com.example.demo.pattern;

import com.example.demo.model.Mueble;


public class MuebleBuilder {

    private String nombre;
    private String tipo;
    private Double precioBase;
    private Integer stock;
    private Mueble.Estado estado;
    private Mueble.Tamano tamano;
    private String material;

    public MuebleBuilder() {
        this.estado = Mueble.Estado.ACTIVO;
    }

    public MuebleBuilder setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public MuebleBuilder setTipo(String tipo) {
        this.tipo = tipo;
        return this;
    }

    public MuebleBuilder setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
        return this;
    }

    public MuebleBuilder setStock(Integer stock) {
        this.stock = stock;
        return this;
    }

    public MuebleBuilder setEstado(Mueble.Estado estado) {
        this.estado = estado;
        return this;
    }

    public MuebleBuilder setTamano(Mueble.Tamano tamano) {
        this.tamano = tamano;
        return this;
    }

    public MuebleBuilder setMaterial(String material) {
        this.material = material;
        return this;
    }

    public Mueble build() {
        Mueble mueble = new Mueble();
        mueble.setNombre(this.nombre);
        mueble.setTipo(this.tipo);
        mueble.setPrecioBase(this.precioBase);
        mueble.setStock(this.stock);
        mueble.setEstado(this.estado);
        mueble.setTamano(this.tamano);
        mueble.setMaterial(this.material);
        return mueble;
    }
}
