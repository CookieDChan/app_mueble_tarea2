package com.example.demo.model;

public class ItemCotizacion {
    private Long muebleId;
    private Long varianteId;
    private Integer cantidad;

    public ItemCotizacion() {
    }

    public ItemCotizacion(Long muebleId, Long varianteId, Integer cantidad) {
        this.muebleId = muebleId;
        this.varianteId = varianteId;
        this.cantidad = cantidad;
    }

    public Long getMuebleId() {
        return muebleId;
    }

    public void setMuebleId(Long muebleId) {
        this.muebleId = muebleId;
    }

    public Long getVarianteId() {
        return varianteId;
    }

    public void setVarianteId(Long varianteId) {
        this.varianteId = varianteId;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
