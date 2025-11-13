package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.model.Cotizacion;
import com.example.demo.model.DetalleCotizacion;
import com.example.demo.model.ItemCotizacion;
import com.example.demo.model.Mueble;
import com.example.demo.model.Variante;
import com.example.demo.repository.CotizacionRepository;

@Service
public class CotizacionService {

    @Autowired
    private CotizacionRepository cotizacionRepository;

    @Autowired
    private MuebleService muebleService;

    @Autowired
    private VarianteService varianteService;

    @Transactional
    public Cotizacion crearCotizacion(List<ItemCotizacion> items) {
        Cotizacion cotizacion = new Cotizacion();

        for (ItemCotizacion item : items) {
            Mueble mueble = muebleService.obtenerPorId(item.getMuebleId());
            if (mueble == null) {
                throw new RuntimeException("Mueble no encontrado con id: " + item.getMuebleId());
            }

            Variante variante = null;
            if (item.getVarianteId() != null) {
                variante = varianteService.obtenerPorId(item.getVarianteId());
            }

            DetalleCotizacion detalle = new DetalleCotizacion(mueble, variante, item.getCantidad());
            cotizacion.agregarDetalle(detalle);
        }

        cotizacion.calcularTotal();
        return cotizacionRepository.save(cotizacion);
    }

    @Transactional
    public Cotizacion confirmarVenta(Long cotizacionId) {
        Cotizacion cotizacion = cotizacionRepository.findById(cotizacionId).orElse(null);
        if (cotizacion == null) {
            throw new RuntimeException("Cotizacion no encontrada");
        }

        if (cotizacion.getEstado() == Cotizacion.EstadoCotizacion.VENTA) {
            throw new RuntimeException("La cotizacion ya fue confirmada como venta");
        }

        for (DetalleCotizacion detalle : cotizacion.getDetalles()) {
            if (!muebleService.hayStock(detalle.getMueble().getId(), detalle.getCantidad())) {
                throw new RuntimeException("Stock insuficiente para el mueble: " + detalle.getMueble().getNombre());
            }
        }

        for (DetalleCotizacion detalle : cotizacion.getDetalles()) {
            muebleService.actualizarStock(detalle.getMueble().getId(), detalle.getCantidad());
        }

        cotizacion.setEstado(Cotizacion.EstadoCotizacion.VENTA);
        return cotizacionRepository.save(cotizacion);
    }

    public List<Cotizacion> listarTodas() {
        return cotizacionRepository.findAll();
    }

    public Cotizacion obtenerPorId(Long id) {
        return cotizacionRepository.findById(id).orElse(null);
    }
}
