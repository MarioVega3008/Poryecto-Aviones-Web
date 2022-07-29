package com.avion.web.avionweb.dto;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PasajeroDTO extends NewPasajeroDTO {
    private Long id;
    private List<AsientoDTO> asientos;
}
