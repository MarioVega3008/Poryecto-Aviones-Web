package com.avion.web.avionweb.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avion.web.avionweb.dto.NewPasajeroDTO;
import com.avion.web.avionweb.dto.PasajeroDTO;
import com.avion.web.avionweb.dto.PasajeroAvionDTO;
import com.avion.web.avionweb.exceptions.NoContentException;
import com.avion.web.avionweb.exceptions.ResourceNotFoundException;
import com.avion.web.avionweb.models.Avion;
import com.avion.web.avionweb.models.Pasajero;
import com.avion.web.avionweb.repositories.AvionRepository;
import com.avion.web.avionweb.repositories.PasajeroRepository;
import com.avion.web.avionweb.services.PasajeroService;

@Service
public class PasajeroServiceImpl implements PasajeroService {

    final ModelMapper modelMapper;
    final PasajeroRepository repository;
    final AvionRepository avionRepository;

    public PasajeroServiceImpl(PasajeroRepository r, AvionRepository er, ModelMapper m)
    {
        this.modelMapper = m;
        this.repository = r;
        this.avionRepository = er;
    }


    @Override
    @Transactional
    public PasajeroDTO create(Long idAvion, NewPasajeroDTO pasajeroDTO) {
        Avion avion = avionRepository.findById(idAvion)
            .orElseThrow(()-> new ResourceNotFoundException("Avion not found"));
        Pasajero pasajero = modelMapper.map(pasajeroDTO, Pasajero.class);    
        pasajero.setAvion(avion);
        repository.save(pasajero);
        return modelMapper.map(pasajero, PasajeroDTO.class); 
    }

    @Override
    @Transactional(readOnly=true)
    public PasajeroAvionDTO retrieve(Long idAvion, Long id) {
        Avion avion = avionRepository.findById(idAvion)
            .orElseThrow(()-> new ResourceNotFoundException("Avion not found"));
        Pasajero pasajero = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Pasajero not found"));
        pasajero.setAvion(avion);
        return modelMapper.map(pasajero, PasajeroAvionDTO.class);
    }

    @Override
    @Transactional
    public PasajeroAvionDTO update(PasajeroDTO pasajeroDTO, Long idAvion, Long id) {
        Avion avion = avionRepository.findById(idAvion)
        .orElseThrow(()-> new ResourceNotFoundException("Avion not found"));
        Pasajero pasajero = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Pasajero not found"));
        pasajero = modelMapper.map(pasajeroDTO, Pasajero.class);
        pasajero.setAvion(avion);
        repository.save(pasajero);       
        return modelMapper.map(pasajero, PasajeroAvionDTO.class);
    }


    @Override
    @Transactional
    public void delete(Long idAvion, Long id) {
        Avion avion = avionRepository.findById(idAvion)
        .orElseThrow(()-> new ResourceNotFoundException("Avion not found"));
        Pasajero pasajero = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Pasajero not found"));
        pasajero.setAvion(avion);
        repository.deleteById(pasajero.getId());  
    }

    @Override
    @Transactional(readOnly=true)
    public List<PasajeroDTO> list(Long idAvion) {
        Avion avion = avionRepository.findById(idAvion)
            .orElseThrow(()-> new ResourceNotFoundException("Avion not found"));
        List<Pasajero> pasajeros = repository.findByAvion(avion);
        if(pasajeros.isEmpty()) throw new NoContentException("Pasajeros is empty");
        //Lambda ->
        return pasajeros.stream().map(q -> modelMapper.map(q, PasajeroDTO.class) )
            .collect(Collectors.toList());
    }


    
    
}
