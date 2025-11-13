package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cotizaciones")
public class Cotizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCotizacion estado;

    @Column(nullable = false)
    private Double total;

    @OneToMany(mappedBy = "cotizacion", cascade = CascadeType.ALL)
    private List<DetalleCotizacion> detalles;

    public enum EstadoCotizacion {
        COTIZACION, VENTA
    }

    public Cotizacion() {
        this.fecha = LocalDateTime.now();
        this.estado = EstadoCotizacion.COTIZACION;
        this.detalles = new ArrayList<>();
        this.total = 0.0;
    }

    public void agregarDetalle(DetalleCotizacion detalle) {
        detalles.add(detalle);
        detalle.setCotizacion(this);
    }

    public void calcularTotal() {
        this.total = detalles.stream()
                .mapToDouble(DetalleCotizacion::getSubtotal)
                .sum();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public EstadoCotizacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoCotizacion estado) {
        this.estado = estado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<DetalleCotizacion> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleCotizacion> detalles) {
        this.detalles = detalles;
    }
}
