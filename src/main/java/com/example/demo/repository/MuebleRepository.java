package com.example.demo.repository;

import com.example.demo.model.Mueble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MuebleRepository extends JpaRepository<Mueble, Long> {
    List<Mueble> findByEstado(Mueble.Estado estado);
}
