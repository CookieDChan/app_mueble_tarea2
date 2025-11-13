package com.example.demo.controller;

import com.example.demo.model.Variante;
import com.example.demo.service.VarianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/variantes")
public class VarianteController {

    @Autowired
    private VarianteService varianteService;

    @PostMapping
    public ResponseEntity<Variante> crearVariante(@RequestBody Variante variante) {
        Variante nuevaVariante = varianteService.crearVariante(variante);
        return new ResponseEntity<>(nuevaVariante, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Variante>> listarTodas() {
        List<Variante> variantes = varianteService.listarTodas();
        return ResponseEntity.ok(variantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Variante> obtenerPorId(@PathVariable Long id) {
        Variante variante = varianteService.obtenerPorId(id);
        if (variante != null) {
            return ResponseEntity.ok(variante);
        }
        return ResponseEntity.notFound().build();
    }
}
