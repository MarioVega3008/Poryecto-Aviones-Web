package com.avion.web.avionweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avion.web.avionweb.models.Asiento;
import com.avion.web.avionweb.models.Pasajero;

public interface AsientoRepository extends JpaRepository<Asiento, Long> {

    public List<Asiento> findByQuestion(Pasajero pasajero);
    
}
