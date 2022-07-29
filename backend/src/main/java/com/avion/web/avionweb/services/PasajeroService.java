package com.avion.web.avionweb.services;

import java.util.List;

import com.avion.web.avionweb.dto.NewPasajeroDTO;
import com.avion.web.avionweb.dto.PasajeroDTO;
import com.avion.web.avionweb.dto.PasajeroAvionDTO;

public interface PasajeroService {
    public PasajeroDTO create(Long idAvion, NewPasajeroDTO pasajeroDTO);
    public PasajeroAvionDTO retrieve(Long idAvion, Long id);
    public PasajeroAvionDTO update(PasajeroDTO pasajeroDTO, Long idAvion, Long id);
    public void delete(Long idAvion, Long id);

    public List<PasajeroDTO> list(Long idAvion);
}
