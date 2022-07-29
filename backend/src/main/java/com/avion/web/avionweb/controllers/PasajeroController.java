package com.avion.web.avionweb.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avion.web.avionweb.dto.NewPasajeroDTO;
import com.avion.web.avionweb.dto.PasajeroDTO;
import com.avion.web.avionweb.dto.PasajeroAvionDTO;
import com.avion.web.avionweb.services.PasajeroService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/avioness")
public class PasajeroController {
    
    final PasajeroService service;

    public PasajeroController(PasajeroService srv){
        this.service = srv;
    }

    /* ================ CREATE ================ */
    @PostMapping("/{id}/pasajeros")
    public ResponseEntity<PasajeroDTO> create(@PathVariable("id") Long id, @Valid @RequestBody NewPasajeroDTO pasajeroDTO){
        PasajeroDTO pasajerDTO = service.create(id, pasajeroDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(pasajerDTO);        
    }

    /* ================ RETRIEVE ================ */
    @GetMapping("/{idAvion}/pasajeros/{id}")
    public ResponseEntity<PasajeroAvionDTO> retrive(@PathVariable("idAvion") Long idAvion, @PathVariable("id") Long id){
        PasajeroAvionDTO result = service.retrieve(idAvion, id);
        return ResponseEntity.ok().body(result);        
    }

    /* ================ UPDATE ================ */
    @PutMapping("/{idAvion}/pasajeros/{id}")
    public ResponseEntity<PasajeroAvionDTO> update(@RequestBody PasajeroDTO pasajeroDTO, @PathVariable("idAvion") Long idAvion, @PathVariable("id") Long id){
        PasajeroAvionDTO result = service.update(pasajeroDTO, idAvion, id);
        return ResponseEntity.ok().body(result);
    }

    /* ================ DELETE ================ */
    @DeleteMapping("/{idAvion}/pasajeros/{id}")
    public ResponseEntity<Void> delete(@PathVariable("idAvion") Long idAvion, @PathVariable("id") Long id){
        service.delete(idAvion, id);
        return ResponseEntity.noContent().build();
    }

    /* ================ LIST ================ */
    @GetMapping("/{id}/pasajeros")
    public ResponseEntity<List<PasajeroDTO>> list(@PathVariable("id") Long id){
        List<PasajeroDTO> pasajeros = service.list(id);
        return ResponseEntity.ok().body(pasajeros);        
    }

}
