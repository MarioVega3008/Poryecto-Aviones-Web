package com.avion.web.avionweb.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.avion.web.avionweb.dto.NewAsientoDTO;
import com.avion.web.avionweb.dto.AsientoDTO;
import com.avion.web.avionweb.exceptions.NoContentException;
import com.avion.web.avionweb.exceptions.ResourceNotFoundException;
import com.avion.web.avionweb.models.Avion;
import com.avion.web.avionweb.models.Asiento;
import com.avion.web.avionweb.models.Pasajero;
import com.avion.web.avionweb.repositories.AvionRepository;
import com.avion.web.avionweb.repositories.AsientoRepository;
import com.avion.web.avionweb.repositories.PasajeroRepository;
import com.avion.web.avionweb.services.AsientoService;

@Service
public class AsientoServiceImpl implements AsientoService {

    final ModelMapper modelMapper;
    final AsientoRepository repository;
    final PasajeroRepository pasajeroRepository;
    final AvionRepository avionRepository;

    public AsientoServiceImpl(AsientoRepository r, PasajeroRepository qr, AvionRepository er, ModelMapper m)
    {
        this.modelMapper = m;
        this.repository = r;
        this.avionRepository = er;
        this.pasajeroRepository = qr;
    }

    @Override
    @Transactional
    public List<AsientoDTO> create(Long idAvion, Long idPasajero, List<NewAsientoDTO> asientos) {
        Avion avion = avionRepository.findById(idAvion).orElseThrow(()-> new ResourceNotFoundException("Avion not found"));
        Pasajero pasajero = pasajeroRepository.findById(idPasajero).orElseThrow(()-> new ResourceNotFoundException("Pasajero not found"));
        pasajero.setAvion(avion);
        List<AsientoDTO> result = new ArrayList<AsientoDTO>();
        for(NewAsientoDTO op : asientos){
            Asiento option = modelMapper.map(op, Asiento.class);
            option.setPasajero(pasajero);
            repository.save(option);
            result.add(modelMapper.map(option, AsientoDTO.class));
        }
        /*asientos.forEach(op -> {
        });        */
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AsientoDTO> list(Long idAvion, Long idPasajero) {
        Avion avion = avionRepository.findById(idAvion).orElseThrow(()-> new ResourceNotFoundException("Avion not found"));
        Pasajero pasajero = pasajeroRepository.findById(idPasajero).orElseThrow(()-> new ResourceNotFoundException("Pasajero not found"));
        pasajero.setAvion(avion);
        if(pasajero.getAsientos().isEmpty()) throw new NoContentException("asientos is empty");
        return pasajero.getAsientos().stream().map(op -> modelMapper.map(op, AsientoDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void remove(Long idAvion, Long idPasajero) {
        Avion avion = avionRepository.findById(idAvion).orElseThrow(()-> new ResourceNotFoundException("Avion not found"));
        Pasajero pasajero = pasajeroRepository.findById(idPasajero).orElseThrow(()-> new ResourceNotFoundException("Pasajero not found"));
        pasajero.setAvion(avion);
        if(pasajero.getAsientos().isEmpty()) throw new NoContentException("asientos is empty");
        pasajero.getAsientos().forEach(op -> {
            repository.delete(op);            
        });                      
    }
    
}
