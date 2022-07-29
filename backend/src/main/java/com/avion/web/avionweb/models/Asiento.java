package com.avion.web.avionweb.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="TBL_AVIONES")
@Getter
@Setter
public class Asiento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CORRECT")
    private boolean correct;

    @ManyToOne
    @JoinColumn(name="PASAJERO_ID", nullable=false)
    private Pasajero pasajero;    
}
