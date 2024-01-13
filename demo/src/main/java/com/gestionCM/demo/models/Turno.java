package com.gestionCM.demo.models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Turno {
    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private Profesional profesional;
    @ManyToOne
    private Consultorio consultorio;
    @ManyToOne
    private Especialidad especialidad;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime fechayHoraTurno;

}
