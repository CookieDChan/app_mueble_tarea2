package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "muebles")
public class Mueble {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Double precioBase;

    @Column(nullable = false)
    private Integer stock;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Estado estado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Tamano tamano;

    @Column(nullable = false)
    private String material;

    public enum Estado {
        ACTIVO, INACTIVO
    }

    public enum Tamano {
        GRANDE, MEDIANO, PEQUENO
    }

    public Mueble() {
        this.estado = Estado.ACTIVO;
    }

    public Mueble(String nombre, String tipo, Double precioBase, Integer stock, Tamano tamano, String material) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.precioBase = precioBase;
        this.stock = stock;
        this.estado = Estado.ACTIVO;
        this.tamano = tamano;
        this.material = material;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(Double precioBase) {
        this.precioBase = precioBase;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Tamano getTamano() {
        return tamano;
    }

    public void setTamano(Tamano tamano) {
        this.tamano = tamano;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
