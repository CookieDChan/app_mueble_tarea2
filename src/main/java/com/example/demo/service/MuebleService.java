package com.example.demo.service;

import com.example.demo.model.Mueble;
import com.example.demo.repository.MuebleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MuebleService {

    @Autowired
    private MuebleRepository muebleRepository;

    public Mueble crearMueble(Mueble mueble) {
        return muebleRepository.save(mueble);
    }

    public List<Mueble> listarTodos() {
        return muebleRepository.findAll();
    }

    public List<Mueble> listarActivos() {
        return muebleRepository.findByEstado(Mueble.Estado.ACTIVO);
    }

    public Mueble obtenerPorId(Long id) {
        return muebleRepository.findById(id).orElse(null);
    }

    public Mueble actualizarMueble(Long id, Mueble muebleActualizado) {
        Mueble mueble = muebleRepository.findById(id).orElse(null);
        if (mueble != null) {
            mueble.setNombre(muebleActualizado.getNombre());
            mueble.setTipo(muebleActualizado.getTipo());
            mueble.setPrecioBase(muebleActualizado.getPrecioBase());
            mueble.setStock(muebleActualizado.getStock());
            mueble.setEstado(muebleActualizado.getEstado());
            mueble.setTamano(muebleActualizado.getTamano());
            mueble.setMaterial(muebleActualizado.getMaterial());
            return muebleRepository.save(mueble);
        }
        return null;
    }

    public boolean desactivarMueble(Long id) {
        Mueble mueble = muebleRepository.findById(id).orElse(null);
        if (mueble != null) {
            mueble.setEstado(Mueble.Estado.INACTIVO);
            muebleRepository.save(mueble);
            return true;
        }
        return false;
    }

    public void actualizarStock(Long id, Integer cantidad) {
        Mueble mueble = muebleRepository.findById(id).orElse(null);
        if (mueble != null) {
            mueble.setStock(mueble.getStock() - cantidad);
            muebleRepository.save(mueble);
        }
    }

    public boolean hayStock(Long id, Integer cantidad) {
        Mueble mueble = muebleRepository.findById(id).orElse(null);
        return mueble != null && mueble.getStock() >= cantidad;
    }
}
