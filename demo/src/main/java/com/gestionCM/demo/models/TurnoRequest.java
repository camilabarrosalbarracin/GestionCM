package com.gestionCM.demo.models;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TurnoRequest {
    private Long pacienteId;
    private Long profesionalId;
    private Long consultorioId;
    private Long especialidadId;
    private LocalDateTime fechayHoraTurno;
}

