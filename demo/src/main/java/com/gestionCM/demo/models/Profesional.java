package com.gestionCM.demo.models;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Set;

@Data
@Entity
public class Profesional {
    private String nombreProfesional;
    private String apellidoProfesional;
    private String matricula;
    @ManyToMany
    private Set<Especialidad> especialidades;
    private int horarioEntrada;
    private int horarioSalida;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
