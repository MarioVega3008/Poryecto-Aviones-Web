package com.avion.web.avionweb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewPasajeroDTO {
    private String name;
    private String description;
    private short score;
}
