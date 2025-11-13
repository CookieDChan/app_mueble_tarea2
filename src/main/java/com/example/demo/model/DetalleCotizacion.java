package com.example.demo.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "detalle_cotizacion")
public class DetalleCotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cotizacion_id", nullable = false)
    @JsonIgnore
    private Cotizacion cotizacion;

    @ManyToOne
    @JoinColumn(name = "mueble_id", nullable = false)
    private Mueble mueble;

    @ManyToOne
    @JoinColumn(name = "variante_id")
    private Variante variante;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Double precioUnitario;

    @Column(nullable = false)
    private Double subtotal;

    public DetalleCotizacion() {
    }

    public DetalleCotizacion(Mueble mueble, Variante variante, Integer cantidad) {
        this.mueble = mueble;
        this.variante = variante;
        this.cantidad = cantidad;
        this.calcularPrecios();
    }

    public void calcularPrecios() {
        this.precioUnitario = mueble.getPrecioBase();
        if (variante != null) {
            this.precioUnitario += variante.getPrecioAdicional();
        }
        this.subtotal = this.precioUnitario * this.cantidad;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cotizacion getCotizacion() {
        return cotizacion;
    }

    public void setCotizacion(Cotizacion cotizacion) {
        this.cotizacion = cotizacion;
    }

    public Mueble getMueble() {
        return mueble;
    }

    public void setMueble(Mueble mueble) {
        this.mueble = mueble;
    }

    public Variante getVariante() {
        return variante;
    }

    public void setVariante(Variante variante) {
        this.variante = variante;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
