package com.avion.web.avionweb.services;

import java.util.List;

import com.avion.web.avionweb.dto.AvionDTO;
import com.avion.web.avionweb.dto.AvionListDTO;
import com.avion.web.avionweb.dto.NewAvionDTO;

public interface AvionService {
    
    public AvionDTO create(NewAvionDTO avionDTO);
    public AvionDTO retrieve(Long id);
    public AvionDTO update(AvionDTO avionDTO, Long id);
    public void delete(Long id);
    public long count();

    public List<AvionListDTO> list(int page, int size, String sort);
}
