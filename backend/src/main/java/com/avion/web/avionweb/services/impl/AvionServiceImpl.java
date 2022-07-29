package com.avion.web.avionweb.services.impl;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.avion.web.avionweb.dto.AvionDTO;
import com.avion.web.avionweb.dto.AvionListDTO;
import com.avion.web.avionweb.dto.NewAvionDTO;
import com.avion.web.avionweb.exceptions.NoContentException;
import com.avion.web.avionweb.exceptions.ResourceNotFoundException;
import com.avion.web.avionweb.models.Avion;
import com.avion.web.avionweb.repositories.AvionRepository;
import com.avion.web.avionweb.services.AvionService;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AvionServiceImpl implements AvionService {

    final ModelMapper modelMapper;
    final AvionRepository avionRepository;

    public AvionServiceImpl(AvionRepository repository, ModelMapper mapper){
        this.avionRepository = repository;
        this.modelMapper = mapper;
    }

    @Override
    @Transactional
    public AvionDTO create(NewAvionDTO avionDTO) {
        Avion avion = modelMapper.map(avionDTO, Avion.class);
        avionRepository.save(avion);        
        return modelMapper.map(avion, AvionDTO.class); 
    }

    @Override
    @Transactional(readOnly = true)
    public AvionDTO retrieve(Long id) {
        Avion avion = avionRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Avion not found"));
        return modelMapper.map(avion, AvionDTO.class);
    }

    @Override
    @Transactional
    public AvionDTO update(AvionDTO avionDTO, Long id) {
        Avion avion = avionRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Avion not found"));        
              
        Avion avionUpdated = modelMapper.map(avionDTO, Avion.class);
        //Keeping values
        avionUpdated.setCreatedBy(avion.getCreatedBy());
        avionUpdated.setCreatedDate(avion.getCreatedDate());
        avionRepository.save(avionUpdated);   
        return modelMapper.map(avionUpdated, AvionDTO.class);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Avion avion = avionRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Avion not found"));        
        avionRepository.deleteById(avion.getId());        
    }

    @Override
    @Transactional(readOnly = true)
    public List<AvionListDTO> list(int page, int size, String sort) {
        Pageable pageable = sort == null || sort.isEmpty() ? 
                    PageRequest.of(page, size) 
                :   PageRequest.of(page, size,  Sort.by(sort));

        Page<Avion> avions = avionRepository.findAll(pageable);
        if(avions.isEmpty()) throw new NoContentException("Avions is empty");
        return avions.stream().map(avion -> modelMapper.map(avion, AvionListDTO.class))
            .collect(Collectors.toList());        
    }

    @Override
    public long count() {        
        return avionRepository.count();
    }
    
}
