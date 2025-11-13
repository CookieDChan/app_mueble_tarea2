package com.example.demo.controller;

import com.example.demo.model.Cotizacion;
import com.example.demo.model.ItemCotizacion;
import com.example.demo.service.CotizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cotizaciones")
public class CotizacionController {

    @Autowired
    private CotizacionService cotizacionService;

    @PostMapping
    public ResponseEntity<?> crearCotizacion(@RequestBody List<ItemCotizacion> items) {
        try {
            Cotizacion cotizacion = cotizacionService.crearCotizacion(items);
            return new ResponseEntity<>(cotizacion, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<Cotizacion>> listarTodas() {
        List<Cotizacion> cotizaciones = cotizacionService.listarTodas();
        return ResponseEntity.ok(cotizaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cotizacion> obtenerPorId(@PathVariable Long id) {
        Cotizacion cotizacion = cotizacionService.obtenerPorId(id);
        if (cotizacion != null) {
            return ResponseEntity.ok(cotizacion);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/confirmar")
    public ResponseEntity<?> confirmarVenta(@PathVariable Long id) {
        try {
            Cotizacion venta = cotizacionService.confirmarVenta(id);
            return ResponseEntity.ok(venta);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
