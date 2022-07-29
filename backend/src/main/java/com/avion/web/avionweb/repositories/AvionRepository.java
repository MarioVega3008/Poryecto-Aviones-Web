package com.avion.web.avionweb.repositories;

import java.util.List;

import com.avion.web.avionweb.models.Avion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvionRepository extends JpaRepository<Avion, Long> {
    
    public List<Avion> findByTitle(String criteria);
}
