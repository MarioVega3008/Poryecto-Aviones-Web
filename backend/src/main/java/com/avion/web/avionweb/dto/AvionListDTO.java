package com.avion.web.avionweb.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AvionListDTO {
    private Long id;
    private String title;
    private String description;
    private short timeLimit;   
    private short numberOfQuestions;  
}
