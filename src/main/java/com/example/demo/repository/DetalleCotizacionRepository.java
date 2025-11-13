package com.example.demo.repository;

import com.example.demo.model.DetalleCotizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCotizacionRepository extends JpaRepository<DetalleCotizacion, Long> {
}
