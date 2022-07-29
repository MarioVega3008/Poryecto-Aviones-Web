package com.avion.web.avionweb.services;

import java.util.List;

import com.avion.web.avionweb.dto.NewAsientoDTO;
import com.avion.web.avionweb.dto.AsientoDTO;

public interface AsientoService {
    public List<AsientoDTO> create(Long idExam, Long idQuestion, List<NewAsientoDTO> list);
    public List<AsientoDTO> list(Long idExam, Long idQuestion);
    public void remove(Long idExam, Long idQuestion);
}
