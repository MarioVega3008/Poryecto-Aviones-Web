package com.avion.web.avionweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.avion.web.avionweb.models.Avion;
import com.avion.web.avionweb.models.Pasajero;

public interface PasajeroRepository extends JpaRepository<Pasajero, Long> {

    public List<Pasajero> findByAvion(Avion avion);
    
}
