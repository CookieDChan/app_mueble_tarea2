package com.example.demo.controller;

import com.example.demo.model.Mueble;
import com.example.demo.service.MuebleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/muebles")
public class MuebleController {

    @Autowired
    private MuebleService muebleService;

    @PostMapping
    public ResponseEntity<Mueble> crearMueble(@RequestBody Mueble mueble) {
        Mueble nuevoMueble = muebleService.crearMueble(mueble);
        return new ResponseEntity<>(nuevoMueble, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Mueble>> listarTodos() {
        List<Mueble> muebles = muebleService.listarTodos();
        return ResponseEntity.ok(muebles);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Mueble>> listarActivos() {
        List<Mueble> muebles = muebleService.listarActivos();
        return ResponseEntity.ok(muebles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mueble> obtenerPorId(@PathVariable Long id) {
        Mueble mueble = muebleService.obtenerPorId(id);
        if (mueble != null) {
            return ResponseEntity.ok(mueble);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mueble> actualizarMueble(@PathVariable Long id, @RequestBody Mueble mueble) {
        Mueble muebleActualizado = muebleService.actualizarMueble(id, mueble);
        if (muebleActualizado != null) {
            return ResponseEntity.ok(muebleActualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> desactivarMueble(@PathVariable Long id) {
        boolean desactivado = muebleService.desactivarMueble(id);
        if (desactivado) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
