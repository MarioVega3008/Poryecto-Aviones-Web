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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avion.web.avionweb.dto.NewAsientoDTO;
import com.avion.web.avionweb.dto.AsientoDTO;
import com.avion.web.avionweb.services.AsientoService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/aviones")
public class AsientoController {
    
    final AsientoService service;

    public AsientoController(AsientoService srv){
        this.service = srv;
    }

    /* ================ CREATE ================ */
    @PostMapping("/{id}/pasajeros/{idPasajero}/asientos")
    public ResponseEntity<List<AsientoDTO>> create(@PathVariable("id") Long id, @PathVariable("idQuestion") Long idQuestion, @Valid @RequestBody List<NewAsientoDTO> asientosDTO){
        List<AsientoDTO> asientoDTOs = service.create(id, idQuestion, asientosDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(asientoDTOs);        
    }

    /* ================ DELETE ================ */
    @DeleteMapping("/{id}/questions/{idQuestion}/asientos")
    public ResponseEntity<List<AsientoDTO>> delete(@PathVariable("id") Long id, @PathVariable("idQuestion") Long idQuestion){
        service.remove(id, idQuestion);
        return ResponseEntity.noContent().build();
    }

    /* ================ LIST ================ */
    @GetMapping("/{id}/questions/{idQuestion}/asientos")
    public ResponseEntity<List<AsientoDTO>> list(@PathVariable("id") Long id, @PathVariable("idQuestion") Long idQuestion){
        List<AsientoDTO> asientoDTOs = service.list(id, idQuestion);
        return ResponseEntity.status(HttpStatus.OK).body(asientoDTOs);        
    }

}
