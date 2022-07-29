package com.avion.web.avionweb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PasajeroAvionDTO extends PasajeroDTO {    
    private AvionDTO exam;
}
