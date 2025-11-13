package com.example.demo.service;

import com.example.demo.model.Variante;
import com.example.demo.repository.VarianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VarianteService {

    @Autowired
    private VarianteRepository varianteRepository;

    public Variante crearVariante(Variante variante) {
        return varianteRepository.save(variante);
    }

    public List<Variante> listarTodas() {
        return varianteRepository.findAll();
    }

    public Variante obtenerPorId(Long id) {
        return varianteRepository.findById(id).orElse(null);
    }

    public Double calcularPrecioConVariante(Double precioBase, Long varianteId) {
        if (varianteId == null) {
            return precioBase;
        }
        Variante variante = varianteRepository.findById(varianteId).orElse(null);
        if (variante != null) {
            return precioBase + variante.getPrecioAdicional();
        }
        return precioBase;
    }
}
