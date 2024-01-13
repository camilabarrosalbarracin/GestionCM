package com.gestionCM.demo.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Consultorio {
    private int numeroConsultorio;
    private String direccionConsultorio;
    private static final int horarioApertura = 8;
    private static final int horarioCierre = 23;
    @ElementCollection(targetClass = DiasSemana.class)
    @Enumerated(EnumType.STRING)
    private List<DiasSemana> diasAbiertos;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
